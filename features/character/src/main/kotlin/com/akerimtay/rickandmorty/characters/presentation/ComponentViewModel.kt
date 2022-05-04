package com.akerimtay.rickandmorty.characters.presentation

import com.akerimtay.rickandmorty.characters.CharacterComponentHolder
import com.akerimtay.rickandmorty.core.presentation.base.BaseViewModel

internal class ComponentViewModel : BaseViewModel() {

    val component = CharacterComponentHolder.getComponent()
}