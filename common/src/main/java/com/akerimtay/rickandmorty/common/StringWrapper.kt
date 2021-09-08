package com.akerimtay.rickandmorty.common

import androidx.annotation.StringRes

data class StringWrapper(
    @StringRes val resId: Int,
    val message: String? = null
)