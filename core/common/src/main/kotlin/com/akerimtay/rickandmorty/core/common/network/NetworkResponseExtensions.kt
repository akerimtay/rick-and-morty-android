package com.akerimtay.rickandmorty.core.common.network

fun <T : Any> BaseResponse<T>.getOrNull(): T? {
    return when (this) {
        is NetworkResponse.Success -> data
        else -> null
    }
}

fun <T : Any> BaseResponse<T>.getOrElse(defaultValue: T): T {
    return when (this) {
        is NetworkResponse.Success -> data
        else -> defaultValue
    }
}

inline fun <T : Any> BaseResponse<T>.getOrElse(defaultValue: () -> T): T {
    return when (this) {
        is NetworkResponse.Success -> data
        else -> defaultValue()
    }
}

// TODO("Improve error handling")
fun <T : Any> BaseResponse<T>.getOrThrow(): T {
    return when (this) {
        is NetworkResponse.Success -> data
        is NetworkResponse.NetworkError -> throw error
        else -> throw RuntimeException()
    }
}