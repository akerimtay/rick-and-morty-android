package com.akerimtay.rickandmorty.character.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akerimtay.rickandmorty.character.domain.usecase.GetAllCharactersAsFlowUseCase
import com.akerimtay.rickandmorty.common.base.Action
import com.akerimtay.rickandmorty.common.base.BaseViewModel
import com.akerimtay.rickandmorty.common.base.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    charactersAsFlowUseCase: GetAllCharactersAsFlowUseCase
) : BaseViewModel() {

    private val _actions = SingleLiveEvent<CharactersAction>()
    val actions: LiveData<CharactersAction> = _actions

    private val _charactersCount = MutableLiveData<Int>(200)
    val charactersCount: LiveData<Int> = _charactersCount
}

sealed class CharactersAction : Action {
    data class ShowToast(val message: String) : CharactersAction()
}