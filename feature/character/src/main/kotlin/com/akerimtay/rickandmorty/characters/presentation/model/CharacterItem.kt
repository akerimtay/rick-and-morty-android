package com.akerimtay.rickandmorty.characters.presentation.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

internal sealed class CharacterItem {

    data class HorizontalItem(
        val name: String,
        val imageUrl: String,
        @StringRes val statusNameResId: Int,
        @ColorRes val statusColorResId: Int,
        val species: String,
        val onItemClickListener: () -> Unit,
    ) : CharacterItem()

    data class GridItem(
        val name: String,
        val imageUrl: String,
        @StringRes val statusNameResId: Int,
        @ColorRes val statusColorResId: Int,
        val species: String,
        val onItemClickListener: () -> Unit,
    ) : CharacterItem()

    data class Header(
        val characterCount: Int,
        val viewType: ListViewType,
        val onChangeViewTypeListener: () -> Unit,
    ) : CharacterItem()
}