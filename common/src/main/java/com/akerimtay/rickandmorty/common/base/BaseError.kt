package com.akerimtay.rickandmorty.common.base

import androidx.annotation.StringRes
import com.akerimtay.rickandmorty.common.R
import com.akerimtay.rickandmorty.common.StringWrapper

private const val NO_RES_ID = 0

abstract class BaseError(
    override val message: String? = null,
    @StringRes open val errorResId: Int = NO_RES_ID
) : Throwable(message) {
    class ApiError(message: String?) : BaseError(message = message)
    object NetworkError : BaseError(errorResId = R.string.error_network)
    object UnknownError : BaseError()

    fun getErrorMessage(@StringRes defaultResId: Int = NO_RES_ID): StringWrapper =
        when {
            !message.isNullOrEmpty() -> StringWrapper(message = message, resId = defaultResId)
            errorResId != NO_RES_ID -> StringWrapper(resId = errorResId)
            else -> StringWrapper(resId = defaultResId)
        }
}