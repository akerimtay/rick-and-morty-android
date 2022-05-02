package com.akerimtay.rickandmorty.characters.data.remote

import com.akerimtay.rickandmorty.core.common.network.getOrThrow
import com.akerimtay.rickandmorty.model.CharacterStatus
import com.akerimtay.rickandmorty.model.Characters
import com.akerimtay.rickandmorty.model.Gender
import javax.inject.Inject

internal class CharacterRemoteDataSourceImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterRemoteDataSource {

    override suspend fun getCharacters(
        page: Int?,
        name: String?,
        status: CharacterStatus?,
        gender: Gender?
    ): Characters {
        val characters = characterService.getCharacters(
            page = page,
            name = name,
            status = status,
            gender = gender
        ).getOrThrow()
        return CharacterMapper.fromNetwork(characters)
    }
}