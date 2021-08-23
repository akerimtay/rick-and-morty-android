package com.akerimtay.rickandmorty.character

import com.akerimtay.common.BaseError

sealed class CharacterError(
    override val message: String? = null
) : BaseError() {
    class EndpointError(message: String?) : CharacterError(message)
    object MappingError : CharacterError()
}