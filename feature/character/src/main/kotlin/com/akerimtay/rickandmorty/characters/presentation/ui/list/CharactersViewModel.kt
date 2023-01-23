package com.akerimtay.rickandmorty.characters.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.recyclerview.widget.RecyclerView
import com.akerimtay.rickandmorty.characters.R
import com.akerimtay.rickandmorty.characters.domain.GetCharactersAsFlowUseCase
import com.akerimtay.rickandmorty.characters.domain.GetCharactersCountAsFlowUseCase
import com.akerimtay.rickandmorty.characters.presentation.model.CharacterItem
import com.akerimtay.rickandmorty.characters.presentation.model.ListViewType
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

internal class CharactersViewModel(
    getCharactersAsFlowUseCase: GetCharactersAsFlowUseCase,
    getCharactersCountAsFlowUseCase: GetCharactersCountAsFlowUseCase
) : BaseViewModel() {

    private val _actions = Channel<CharactersAction>(Channel.BUFFERED)
    val actions: Flow<CharactersAction> = _actions.receiveAsFlow()

    private val loadStates = MutableStateFlow<CombinedLoadStates?>(null)

    val isSwipeRefreshing: Flow<Boolean> = loadStates.map { states -> states?.refresh is LoadState.Loading }

    private val pagingSource = getCharactersAsFlowUseCase(GetCharactersAsFlowUseCase.Param())
        .cachedIn(viewModelScope)

    private val _selectedListViewType = MutableStateFlow(ListViewType.HORIZONTAL)
    val selectedListViewType: StateFlow<ListViewType> = _selectedListViewType.asStateFlow()
    val listViewTypeIcon: Flow<Int> = _selectedListViewType.map { viewType ->
        when (viewType) {
            ListViewType.HORIZONTAL -> R.drawable.ic_bullet_24
            ListViewType.GRID -> R.drawable.ic_grid_24
        }
    }

    val charactersCount: Flow<Int> = getCharactersCountAsFlowUseCase(Unit)

    val items: Flow<PagingData<CharacterItem>> = combine(
        pagingSource,
        _selectedListViewType
    ) { pagingData, viewType ->
        pagingData
            .map { character ->
                CharacterItem(
                    name = character.name,
                    imageUrl = character.imageUrl,
                    statusNameResId = character.status.displayNameResId,
                    statusColorResId = character.status.colorResId,
                    species = character.species,
                    viewType = viewType,
                    onItemClickListener = {

                    }
                )
            }
    }

    private val firstVisibleItemPosition = MutableStateFlow(RecyclerView.NO_POSITION)
    val isScrollToTopVisible = firstVisibleItemPosition
        .mapLatest { it != RecyclerView.NO_POSITION && it > LAST_VISIBLE_POSITION }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun onLoadStates(state: CombinedLoadStates) {
        loadStates.value = state
    }

    fun onChangeViewTypeClicked() {
        _selectedListViewType.value = if (_selectedListViewType.value == ListViewType.HORIZONTAL) {
            ListViewType.GRID
        } else {
            ListViewType.HORIZONTAL
        }
    }

    fun onScrolled(firstVisibleItemPosition: Int) {
        this.firstVisibleItemPosition.value = firstVisibleItemPosition
    }

    fun onScrollToTopClicked() {
        _actions.trySend(CharactersAction.ScrollToPosition(0))
    }

    companion object {

        private const val LAST_VISIBLE_POSITION = 6
    }
}

internal sealed class CharactersAction {
    data class ScrollToPosition(val position: Int) : CharactersAction()
}

internal class CharactersViewModelFactory @Inject constructor(
    private val getCharactersAsFlowUseCase: GetCharactersAsFlowUseCase,
    private val getCharactersCountAsFlowUseCase: GetCharactersCountAsFlowUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(
            getCharactersAsFlowUseCase = getCharactersAsFlowUseCase,
            getCharactersCountAsFlowUseCase = getCharactersCountAsFlowUseCase
        ) as T
    }
}