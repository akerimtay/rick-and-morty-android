package com.akerimtay.rickandmorty.characters.presentation

import androidx.lifecycle.ViewModel
import com.akerimtay.rickandmorty.characters.CharacterComponentHolder

internal class ComponentViewModel : ViewModel() {

    val component = CharacterComponentHolder.getComponent()
}