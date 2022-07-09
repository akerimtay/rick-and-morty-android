package com.akerimtay.rickandmorty.characters.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.akerimtay.rickandmorty.characters.domain.GetCharactersAsFlowUseCase
import com.akerimtay.rickandmorty.characters.domain.GetCharactersCountAsFlowUseCase
import com.akerimtay.rickandmorty.characters.presentation.model.CharacterItem
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
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

    val items: Flow<PagingData<CharacterItem>> =
        getCharactersAsFlowUseCase(GetCharactersAsFlowUseCase.Param())
            .map { pagingData ->
                pagingData.map { character ->
                    CharacterItem(
                        name = character.name,
                        imageUrl = character.imageUrl,
                        status = character.status.displayNameResId,
                        species = character.species
                    )
                }
            }
            .cachedIn(viewModelScope)

    val charactersCount: Flow<Int> = getCharactersCountAsFlowUseCase(Unit)

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