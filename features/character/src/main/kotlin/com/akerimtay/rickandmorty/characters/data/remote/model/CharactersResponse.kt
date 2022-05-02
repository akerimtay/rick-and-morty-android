package com.akerimtay.rickandmorty.characters.data.remote.model

import com.google.gson.annotations.SerializedName

internal data class CharactersResponse(
    @SerializedName("info")
    val infoResponse: InfoResponse,
    @SerializedName("results")
    val results: List<CharacterResponse>
) {
    data class InfoResponse(
        @SerializedName("count")
        val count: Int,
        @SerializedName("pages")
        val pages: Int,
        @SerializedName("next")
        val next: String? = null,
        @SerializedName("prev")
        val prev: String? = null
    )
}