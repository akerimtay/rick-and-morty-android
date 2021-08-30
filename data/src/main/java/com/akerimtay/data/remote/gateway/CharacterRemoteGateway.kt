package com.akerimtay.data.remote.gateway

import com.akerimtay.data.mapper.CharacterMapper
import com.akerimtay.data.remote.service.CharacterService
import com.akerimtay.data.remote.utill.handleResponse
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
        val response = networkResponse.handleResponse()
        return CharacterMapper.fromNetwork(response)
    }

    suspend fun getCharacterById(characterId: Int): Character {
        val networkResponse = characterService.getCharacterAsync(characterId)
        val response = networkResponse.handleResponse()
        return CharacterMapper.fromNetwork(response)
    }
}