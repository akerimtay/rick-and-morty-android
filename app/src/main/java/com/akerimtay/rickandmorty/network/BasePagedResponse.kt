package com.akerimtay.rickandmorty.network

import com.google.gson.annotations.SerializedName

data class BasePagedResponse<T>(
    @SerializedName("info")
    var infoResponse: InfoResponse,
    @SerializedName("results")
    var results: T
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