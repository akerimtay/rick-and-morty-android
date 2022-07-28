package com.akerimtay.rickandmorty.core.common.model

data class Episodes(
    val info: Info,
    val results: List<Episode>
) {

    data class Info(
        val count: Int,
        val pages: Int,
        val next: String?,
        val prev: String?
    )
}