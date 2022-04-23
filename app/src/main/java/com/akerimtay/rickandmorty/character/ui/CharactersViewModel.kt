package com.akerimtay.rickandmorty.character.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.akerimtay.rickandmorty.character.domain.usecase.GetAllCharactersAsFlowUseCase
import com.akerimtay.rickandmorty.common.adapter.BaseContentItem
import com.akerimtay.rickandmorty.common.base.Action
import com.akerimtay.rickandmorty.common.base.BaseViewModel
import com.akerimtay.rickandmorty.common.base.SingleLiveEvent
import com.akerimtay.rickandmorty.content.ItemContentType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@HiltViewModel
class CharactersViewModel @Inject constructor(
    charactersAsFlowUseCase: GetAllCharactersAsFlowUseCase
) : BaseViewModel() {

    private val _actions = SingleLiveEvent<CharactersAction>()
    val actions: LiveData<CharactersAction> = _actions

    private val _charactersCount = MutableLiveData<Int>(200)
    val charactersCount: LiveData<Int> = _charactersCount

    val characters: Flow<PagingData<BaseContentItem<ItemContentType>>> =
        charactersAsFlowUseCase(
            GetAllCharactersAsFlowUseCase.Param()
        ).cachedIn(viewModelScope)
            .map { pagingData ->
                pagingData.map { character ->
                    CharacterItem(
                        character = character,
                        onItemClickListener = {
                            _actions.postValue(CharactersAction.ShowToast(character.name))
                        }
                    )
                }
            }
}

sealed class CharactersAction : Action {
    data class ShowToast(val message: String) : CharactersAction()
}