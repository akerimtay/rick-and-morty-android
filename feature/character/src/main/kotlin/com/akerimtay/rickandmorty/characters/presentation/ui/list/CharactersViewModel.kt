package com.akerimtay.rickandmorty.characters.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertHeaderItem
import androidx.paging.map
import com.akerimtay.rickandmorty.characters.domain.GetCharactersAsFlowUseCase
import com.akerimtay.rickandmorty.characters.domain.GetCharactersCountAsFlowUseCase
import com.akerimtay.rickandmorty.characters.presentation.model.CharacterItem
import com.akerimtay.rickandmorty.characters.presentation.model.ListViewType
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

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


    private val _selectedListViewType = MutableStateFlow(ListViewType.GRID)
    val selectedListViewType: StateFlow<ListViewType> = _selectedListViewType.asStateFlow()



    val items: Flow<PagingData<CharacterItem>> = combine(
        pagingSource,
        getCharactersCountAsFlowUseCase(Unit),
        _selectedListViewType
    ) { pagingData, characterCount, viewType ->
        pagingData
            .map { character ->
                when (viewType) {
                    ListViewType.HORIZONTAL -> CharacterItem.HorizontalItem(
                        name = character.name,
                        imageUrl = character.imageUrl,
                        statusNameResId = character.status.displayNameResId,
                        statusColorResId = character.status.colorResId,
                        species = character.species,
                        onItemClickListener = {

                        }
                    )
                    ListViewType.GRID -> CharacterItem.GridItem(
                        name = character.name,
                        imageUrl = character.imageUrl,
                        statusNameResId = character.status.displayNameResId,
                        statusColorResId = character.status.colorResId,
                        species = character.species,
                        onItemClickListener = {

                        }
                    )
                }
            }.insertHeaderItem(
                item = CharacterItem.Header(
                    characterCount = characterCount,
                    viewType = viewType,
                    onChangeViewTypeListener = {
                        _selectedListViewType.value = if (_selectedListViewType.value == ListViewType.HORIZONTAL) {
                            ListViewType.GRID
                        } else {
                            ListViewType.HORIZONTAL
                        }
                    }
                ) as CharacterItem
            )
    }

    fun onLoadStates(state: CombinedLoadStates) {
        loadStates.value = state
    }
}

internal sealed class CharactersAction {

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