package com.akerimtay.character_details

import androidx.lifecycle.SavedStateHandle
import com.akerimtay.domain.usecase.GetCharactersUseCase
import com.akerimtay.rickandmorty.common.base.BaseViewModel
import com.akerimtay.rickandmorty.navigation.destination.CharacterDetailsDestination
import com.akerimtay.rickandmorty.navigation.navigator.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import timber.log.Timber

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel(), Navigator by navigator {
    val id
        get() = savedStateHandle.get<Long>(CharacterDetailsDestination.CHARACTER_ID_PARAM)
            ?: throw IllegalStateException("Parameter character ID must not be null!")

    init {
        launchSafe(
            body = {
                val characters = getCharactersUseCase(Unit)
            },
            handleError = { Timber.e(it, "Couldn't get characters") }
        )
    }
}