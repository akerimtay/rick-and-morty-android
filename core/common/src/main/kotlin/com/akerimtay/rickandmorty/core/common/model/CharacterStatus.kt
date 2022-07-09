package com.akerimtay.rickandmorty.core.common.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.akerimtay.rickandmorty.core.common.R

enum class CharacterStatus(
    val serializedName: String,
    @StringRes val displayNameResId: Int,
    @ColorRes val colorResId: Int
) {

    ALIVE(serializedName = "alive", R.string.alive, R.color.emerald),
    DEAD(serializedName = "dead", R.string.dead, R.color.burnt_sienna),
    UNKNOWN(serializedName = "unknown", R.string.dead, R.color.burnt_sienna);

    companion object {

        fun toCharacterStatus(value: String): CharacterStatus {
            return values().firstOrNull { it.serializedName == value } ?: UNKNOWN
        }
    }
}