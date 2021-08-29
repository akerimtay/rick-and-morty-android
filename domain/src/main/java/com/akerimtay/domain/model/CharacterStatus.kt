package com.akerimtay.domain.model

enum class CharacterStatus(val serializedName: String) {
    ALIVE(serializedName = "alive"),
    DEAD(serializedName = "dead"),
    UNKNOWN(serializedName = "unknown");

    companion object {
        fun toCharacterStatus(value: String): CharacterStatus =
            values().firstOrNull { it.serializedName == value } ?: UNKNOWN
    }
}