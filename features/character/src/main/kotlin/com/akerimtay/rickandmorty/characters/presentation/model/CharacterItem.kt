package com.akerimtay.rickandmorty.characters.presentation.model

import androidx.annotation.StringRes

data class CharacterItem(
    val name: String,
    val image: String,
    @StringRes val status: Int,
    val species: String,
)