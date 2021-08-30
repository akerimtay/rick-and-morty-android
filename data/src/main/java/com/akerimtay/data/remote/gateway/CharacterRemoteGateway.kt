package com.akerimtay.data.remote.gateway

import com.akerimtay.common.BaseError
import com.akerimtay.data.mapper.CharacterMapper
import com.akerimtay.data.remote.base.BaseResponse
import com.akerimtay.data.remote.base.NetworkResponse
import com.akerimtay.data.remote.service.CharacterService
import com.akerimtay.domain.error.CharacterError
import com.akerimtay.domain.model.Character
import com.akerimtay.domain.model.CharacterStatus
import com.akerimtay.domain.model.Characters
import com.akerimtay.domain.model.Gender
import javax.inject.Inject

class CharacterRemoteGateway @Inject constructor(
    private val characterService: CharacterService
) {
    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        status: CharacterStatus? = null,
        gender: Gender? = null
    ): Characters {
        val networkResponse = characterService.getCharactersAsync(
            page = page,
            name = name,
            status = status,
            gender = gender
        )
        val response = extractResponseData(networkResponse)
        return CharacterMapper.fromNetwork(response)
    }

    suspend fun getCharacterById(characterId: Int): Character {
        val networkResponse = characterService.getCharacterAsync(characterId)
        val response = extractResponseData(networkResponse)
        return CharacterMapper.fromNetwork(response)
    }

    private fun <T : Any> extractResponseData(response: BaseResponse<T>): T {
        return when (response) {
            is NetworkResponse.Success -> response.body
            is NetworkResponse.ApiError -> throw CharacterError.EndpointError(response.body.errorMessage)
            is NetworkResponse.NetworkError -> throw BaseError.NetworkError
            is NetworkResponse.UnknownError -> throw BaseError.UnknownError
        }
    }
}