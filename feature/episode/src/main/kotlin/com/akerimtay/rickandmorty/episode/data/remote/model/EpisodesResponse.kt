package com.akerimtay.rickandmorty.episode.data.remote.model

import com.google.gson.annotations.SerializedName

internal data class EpisodesResponse(
    @SerializedName("info")
    val infoResponse: InfoResponse,
    @SerializedName("results")
    val results: List<EpisodeResponse>
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