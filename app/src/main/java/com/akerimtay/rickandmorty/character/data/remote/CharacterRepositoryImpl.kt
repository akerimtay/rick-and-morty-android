package com.akerimtay.rickandmorty.character.data.remote

import com.akerimtay.rickandmorty.character.data.mapper.CharacterMapper
import com.akerimtay.rickandmorty.character.domain.model.Character
import com.akerimtay.rickandmorty.character.domain.repository.CharacterRepository
import com.akerimtay.rickandmorty.common.util.handleResponse
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterRepository {
    override suspend fun getAll(): List<Character> {
        val networkResponse = characterService.getCharactersAsync()
        val response = networkResponse.handleResponse()
        return CharacterMapper.fromNetwork(response).results
    }
}