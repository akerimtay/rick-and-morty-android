package com.akerimtay.rickandmorty.location.data.remote.model

import com.google.gson.annotations.SerializedName

internal data class LocationsResponse(
    @SerializedName("info")
    val infoResponse: InfoResponse,
    @SerializedName("results")
    val results: List<LocationResponse>
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