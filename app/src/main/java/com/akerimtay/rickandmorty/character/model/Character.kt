package com.akerimtay.rickandmorty.character.model

import android.os.Parcelable
import com.akerimtay.common.model.CharacterStatus
import com.akerimtay.common.model.Gender
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
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: Date?
) : Parcelable