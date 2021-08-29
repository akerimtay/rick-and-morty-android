package com.akerimtay.data.db

import androidx.room.TypeConverter
import com.akerimtay.domain.model.CharacterStatus
import java.util.Date

class Converters {
    @TypeConverter
    fun dateFromTimestamp(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun fromCharacterStatus(status: CharacterStatus): String = status.serializedName

    fun stringToCharacterStatus(value: String?) = value?.let { CharacterStatus.toCharacterStatus(it) }
}