package com.akerimtay.rickandmorty.episode.presentation.model

import androidx.annotation.DrawableRes

data class EpisodeItem(
    val id: Int,
    val name: String,
    val season: String,
    val date: String,
    @DrawableRes val imageResId: Int,
    val onItemClickListener: () -> Unit,
)