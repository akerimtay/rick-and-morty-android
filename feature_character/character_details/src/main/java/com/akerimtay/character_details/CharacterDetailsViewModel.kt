package com.akerimtay.character_details

import androidx.lifecycle.SavedStateHandle
import com.akerimtay.common.base.BaseViewModel
import com.akerimtay.navigation.destination.CharacterDetailsDestination
import com.akerimtay.navigation.navigator.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import timber.log.Timber

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
) : BaseViewModel(), Navigator by navigator {
    val id
        get() = savedStateHandle.get<Long>(CharacterDetailsDestination.CHARACTER_ID_PARAM)
            ?: throw IllegalStateException("Parameter character ID must not be null!")

    init {
        Timber.e("characterId = $id")
    }
}