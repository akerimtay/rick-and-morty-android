package com.akerimtay.rickandmorty.common.base.network

import com.google.gson.annotations.SerializedName

typealias BaseResponse<S> = NetworkResponse<S, BaseErrorResponse>

data class BaseErrorResponse(
    @SerializedName("error")
    val errorMessage: String
)