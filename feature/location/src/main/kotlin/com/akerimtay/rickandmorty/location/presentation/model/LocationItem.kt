package com.akerimtay.rickandmorty.location.presentation.model

import androidx.annotation.DrawableRes

data class LocationItem(
    val id: Int,
    @DrawableRes val imageResId: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val onItemClickListener: () -> Unit,
)