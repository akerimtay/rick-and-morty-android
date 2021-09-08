package com.akerimtay.data.remote.utill

import com.akerimtay.rickandmorty.common.BaseError
import com.akerimtay.data.remote.base.BaseResponse
import com.akerimtay.data.remote.base.NetworkResponse

fun <T : Any> BaseResponse<T>.handleResponse(): T {
    return when (this) {
        is NetworkResponse.Success -> body
        is NetworkResponse.ApiError -> throw BaseError.ApiError(body.errorMessage)
        is NetworkResponse.NetworkError -> throw BaseError.NetworkError
        is NetworkResponse.UnknownError -> throw BaseError.UnknownError
    }
}