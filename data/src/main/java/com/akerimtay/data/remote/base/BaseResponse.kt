package com.akerimtay.data.remote.base

import com.google.gson.annotations.SerializedName

typealias BaseResponse<S> = NetworkResponse<S, BaseErrorResponse>

data class BaseErrorResponse(
    @SerializedName("error")
    val errorMessage: String
)