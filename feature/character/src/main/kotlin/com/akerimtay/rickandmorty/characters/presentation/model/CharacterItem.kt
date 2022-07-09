package com.akerimtay.rickandmorty.characters.presentation.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

data class CharacterItem(
    val name: String,
    val imageUrl: String,
    @StringRes val statusNameResId: Int,
    @ColorRes val statusColorResId: Int,
    val species: String,
)