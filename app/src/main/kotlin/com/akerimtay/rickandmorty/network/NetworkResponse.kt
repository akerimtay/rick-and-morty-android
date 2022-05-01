package com.akerimtay.rickandmorty.network

import com.google.gson.annotations.SerializedName
import java.io.IOException

sealed class NetworkResponse<out T : Any, out U : Any> {
    /**
     * Success response with body
     */
    data class Success<T : Any>(val data: T) : NetworkResponse<T, Nothing>()

    /**
     * Failure response with body
     */
    data class ApiError<U : Any>(val body: U, val code: Int) : NetworkResponse<Nothing, U>()

    /**
     * Network error
     */
    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()

    /**
     * For example, json parsing error
     */
    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
}

typealias BaseResponse<S> = NetworkResponse<S, BaseErrorResponse>

data class BaseErrorResponse(
    @SerializedName("error")
    val errorMessage: String
)