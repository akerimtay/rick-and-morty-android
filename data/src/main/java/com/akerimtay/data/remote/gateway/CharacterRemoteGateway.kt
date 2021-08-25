package com.akerimtay.data.remote.gateway

import com.akerimtay.common.BaseError
import com.akerimtay.data.mapper.CharacterMapper
import com.akerimtay.data.remote.service.CharacterService
import com.akerimtay.domain.error.CharacterError
import com.akerimtay.domain.model.Character
import com.akerimtay.domain.model.CharacterStatus
import com.akerimtay.domain.model.Characters
import com.akerimtay.domain.model.Gender
import javax.inject.Inject
import retrofit2.Response

class CharacterRemoteGateway @Inject constructor(
    private val characterService: CharacterService
) {
    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        status: CharacterStatus? = null,
        gender: Gender? = null
    ): Characters = CharacterMapper.fromNetwork(
        extractResponseData(
            response = characterService.getCharactersAsync(
                page = page,
                name = name,
                status = status,
                gender = gender
            )
        )
    )

    suspend fun getCharacterById(characterId: Int): Character =
        CharacterMapper.fromNetwork(
            extractResponseData(response = characterService.getCharacterAsync(characterId))
        )

    private fun <T> extractResponseData(response: Response<T>): T {
        if (response.isSuccessful) {
            return response.body() ?: throw BaseError.UnknownError
        } else {
            throw CharacterError.EndpointError(response.message())
        }
    }
}