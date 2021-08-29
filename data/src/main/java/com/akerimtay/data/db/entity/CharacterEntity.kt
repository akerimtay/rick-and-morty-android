package com.akerimtay.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.akerimtay.domain.model.CharacterStatus
import com.akerimtay.domain.model.Gender
import java.util.Date

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "status")
    val status: CharacterStatus,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "gender")
    val gender: Gender,
    @ColumnInfo(name = "origin_name")
    val originName: String,
    @ColumnInfo(name = "origin_url")
    val originUrl: String,
    @ColumnInfo(name = "location_name")
    val locationName: String,
    @ColumnInfo(name = "location_url")
    val locationUrl: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "episode")
    val episode: List<String>,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "created_date")
    val createdDate: Date?
)