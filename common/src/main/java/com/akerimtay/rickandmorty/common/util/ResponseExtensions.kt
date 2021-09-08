package com.akerimtay.rickandmorty.common.util

import com.akerimtay.rickandmorty.common.BaseError
import com.akerimtay.rickandmorty.common.base.network.BaseResponse
import com.akerimtay.rickandmorty.common.base.network.NetworkResponse

fun <T : Any> BaseResponse<T>.handleResponse(): T {
    return when (this) {
        is NetworkResponse.Success -> body
        is NetworkResponse.ApiError -> throw BaseError.ApiError(body.errorMessage)
        is NetworkResponse.NetworkError -> throw BaseError.NetworkError
        is NetworkResponse.UnknownError -> throw BaseError.UnknownError
    }
}