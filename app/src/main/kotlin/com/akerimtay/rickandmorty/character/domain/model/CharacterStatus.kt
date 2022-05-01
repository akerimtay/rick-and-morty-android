package com.akerimtay.rickandmorty.character.domain.model

enum class CharacterStatus(val serializedName: String) {
    ALIVE(serializedName = "alive"),
    DEAD(serializedName = "dead"),
    UNKNOWN(serializedName = "unknown");

    companion object {

        fun toCharacterStatus(value: String): CharacterStatus {
            return values().firstOrNull { it.serializedName == value } ?: UNKNOWN
        }
    }
}