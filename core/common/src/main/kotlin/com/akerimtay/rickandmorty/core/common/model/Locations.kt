package com.akerimtay.rickandmorty.core.common.model

data class Locations(
    val info: Info,
    val results: List<Location>
) {

    data class Info(
        val count: Int,
        val pages: Int,
        val next: String?,
        val prev: String?
    )
}