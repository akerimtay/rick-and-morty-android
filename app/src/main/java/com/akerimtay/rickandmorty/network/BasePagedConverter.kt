package com.akerimtay.rickandmorty.network

import com.akerimtay.common.Info

object BasePagedConverter {
    fun fromNetwork(response: BasePagedResponse.InfoResponse): Info =
        Info(
            count = response.count,
            pages = response.pages,
            next = response.next,
            prev = response.prev
        )
}