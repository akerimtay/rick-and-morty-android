package com.akerimtay.rickandmorty.entity

import androidx.annotation.StringRes

enum class CharacterStatus(
    val serializedName: String,
    @StringRes val displayNameResId: Int
) {

    ALIVE(serializedName = "alive", R.string.alive),
    DEAD(serializedName = "dead", R.string.dead),
    UNKNOWN(serializedName = "unknown", R.string.dead);

    companion object {

        fun toCharacterStatus(value: String): CharacterStatus {
            return values().firstOrNull { it.serializedName == value } ?: UNKNOWN
        }
    }
}