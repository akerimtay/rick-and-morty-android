package com.akerimtay.rickandmorty.character.data.remote

import com.akerimtay.rickandmorty.character.data.mapper.CharacterMapper
import com.akerimtay.rickandmorty.character.domain.model.Character
import com.akerimtay.rickandmorty.character.domain.model.CharacterStatus
import com.akerimtay.rickandmorty.character.domain.model.Characters
import com.akerimtay.rickandmorty.character.domain.model.Gender
import com.akerimtay.rickandmorty.common.util.handleResponse
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