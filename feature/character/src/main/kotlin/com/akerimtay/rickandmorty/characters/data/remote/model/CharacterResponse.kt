package com.akerimtay.rickandmorty.characters.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.Date

internal data class CharacterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("origin")
    val origin: LocationResponse,
    @SerializedName("location")
    val location: LocationResponse,
    @SerializedName("image")
    val image: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: Date
) {
    data class LocationResponse(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )
}