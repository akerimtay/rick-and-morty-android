package com.akerimtay.rickandmorty.core.common.model

import android.os.Parcelable
import java.util.Date
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: Gender,
    val origin: Location,
    val location: Location,
    val imageUrl: String,
    val episode: List<String>,
    val url: String,
    val createdDate: Date
) : Parcelable {

    @Parcelize
    data class Location(
        val name: String,
        val url: String
    ) : Parcelable
}