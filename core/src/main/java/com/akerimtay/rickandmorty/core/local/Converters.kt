package com.akerimtay.rickandmorty.core.local

import androidx.room.TypeConverter
import com.akerimtay.rickandmorty.character.domain.model.CharacterStatus
import com.akerimtay.rickandmorty.character.domain.model.Gender
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.Date

class Converters {
    private val gson = GsonBuilder()
        .create()

    @TypeConverter
    fun toDate(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time

    @TypeConverter
    fun fromCharacterStatus(status: CharacterStatus): String = status.serializedName

    @TypeConverter
    fun toCharacterStatus(value: String) = CharacterStatus.toCharacterStatus(value)

    @TypeConverter
    fun fromGender(gender: Gender): String = gender.serializedName

    @TypeConverter
    fun toGender(value: String): Gender = Gender.toGender(value)

    @TypeConverter
    fun fromListString(list: List<String>): String = gson.toJson(list)

    @TypeConverter
    fun toListString(value: String): List<String> = gson.fromJson(value, object : TypeToken<List<String>>() {}.type)
}