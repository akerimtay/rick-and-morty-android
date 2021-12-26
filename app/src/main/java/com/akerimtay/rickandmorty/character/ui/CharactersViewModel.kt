package com.akerimtay.rickandmorty.character.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akerimtay.rickandmorty.character.domain.model.Character
import com.akerimtay.rickandmorty.character.domain.usecase.GetAllCharactersUseCase
import com.akerimtay.rickandmorty.common.base.BaseViewModel
import com.akerimtay.rickandmorty.common.base.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase: GetAllCharactersUseCase
) : BaseViewModel() {

    private val _characters = MutableLiveData<Resource<List<Character>>>()
    val characters: LiveData<Resource<List<Character>>> = _characters

    init {
        launchSafe(
            start = { _characters.postValue(Resource.Loading) },
            body = {
                val characters = charactersUseCase(Unit)
                _characters.postValue(Resource.Success(data = characters))
            },
            handleError = {
                _characters.postValue(Resource.Error(exception = it))
            }
        )
    }
}