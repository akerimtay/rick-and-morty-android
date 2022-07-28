package com.akerimtay.rickandmorty.episode.data.remote.model

import com.google.gson.annotations.SerializedName

internal data class EpisodeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val date: String,
    @SerializedName("episode")
    val code: String,
)