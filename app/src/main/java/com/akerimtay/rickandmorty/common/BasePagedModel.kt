package com.akerimtay.rickandmorty.common

data class BasePagedModel<T>(
    val info: Info,
    val results: T
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)