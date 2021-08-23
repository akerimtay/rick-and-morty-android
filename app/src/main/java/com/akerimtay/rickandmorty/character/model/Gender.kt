package com.akerimtay.rickandmorty.character.model

enum class Gender(val serializedName: String) {
    MALE(serializedName = "male"),
    FEMALE(serializedName = "female"),
    GENDERLESS(serializedName = "genderless"),
    UNKNOWN(serializedName = "unknown");

    companion object {
        fun toGender(value: String): Gender = values().firstOrNull { it.serializedName == value } ?: UNKNOWN
    }
}