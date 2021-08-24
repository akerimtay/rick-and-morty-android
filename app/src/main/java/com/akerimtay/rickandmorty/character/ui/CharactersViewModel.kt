package com.akerimtay.rickandmorty.character.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akerimtay.common.base.BaseViewModel
import com.akerimtay.rickandmorty.character.domain.GetCharactersUseCase
import com.akerimtay.rickandmorty.character.model.Characters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import timber.log.Timber

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel() {
    private val _characters = MutableLiveData<Characters>()
    val characters: LiveData<Characters> = _characters

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        launchSafe(
            start = { _isLoading.postValue(true) },
            finish = { _isLoading.postValue(false) },
            body = {
                val characters = getCharactersUseCase(Unit)
                _characters.postValue(characters)

            },
            handleError = { throwable ->
                Timber.e(throwable, "Couldn't get characters")
            }
        )
    }
}