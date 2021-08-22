package com.akerimtay.rickandmorty.common

import com.akerimtay.rickandmorty.network.BasePagedResponse

object BasePagedConverter {
    fun fromNetwork(response: BasePagedResponse.InfoResponse): Info =
        Info(
            count = response.count,
            pages = response.pages,
            next = response.next,
            prev = response.prev
        )
}