package com.akerimtay.rickandmorty.character.data.api

import com.akerimtay.common.BaseError
import com.akerimtay.rickandmorty.character.CharacterError
import com.akerimtay.rickandmorty.character.data.CharacterConverter
import com.akerimtay.rickandmorty.character.domain.CharacterRemoteGateway
import com.akerimtay.rickandmorty.character.model.Character
import com.akerimtay.common.model.CharacterStatus
import com.akerimtay.rickandmorty.character.model.Characters
import com.akerimtay.common.model.Gender
import javax.inject.Inject
import retrofit2.Response

class CharacterRestApi @Inject constructor(
    private val characterService: CharacterService
) : CharacterRemoteGateway {
    override suspend fun getCharacters(
        page: Int?,
        name: String?,
        status: CharacterStatus?,
        gender: Gender?
    ): Characters = CharacterConverter.fromNetwork(
        extractResponseData(
            response = characterService.getCharactersAsync(
                page = page,
                name = name,
                status = status,
                gender = gender
            )
        )
    )

    override suspend fun getCharacterById(characterId: Int): Character = CharacterConverter.fromNetwork(
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