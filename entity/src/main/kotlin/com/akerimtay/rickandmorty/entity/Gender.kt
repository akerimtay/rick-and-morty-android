package com.akerimtay.rickandmorty.entity

enum class Gender(val serializedName: String) {
    MALE(serializedName = "male"),
    FEMALE(serializedName = "female"),
    GENDERLESS(serializedName = "genderless"),
    UNKNOWN(serializedName = "unknown");

    companion object {

        fun toGender(value: String): Gender {
            return values().firstOrNull { it.serializedName == value } ?: UNKNOWN
        }
    }
}