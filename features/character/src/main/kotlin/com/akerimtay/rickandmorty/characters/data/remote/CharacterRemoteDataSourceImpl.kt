package com.akerimtay.rickandmorty.characters.data.remote

import com.akerimtay.rickandmorty.core.common.network.getOrThrow
import com.akerimtay.rickandmorty.entity.CharacterStatus
import com.akerimtay.rickandmorty.entity.Characters
import com.akerimtay.rickandmorty.entity.Gender
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class CharacterRemoteDataSourceImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterRemoteDataSource {

    private val _charactersCount = MutableStateFlow(INITIAL_CHARACTERS_COUNT)
    override val charactersCount: Flow<Int> = _charactersCount

    override suspend fun getCharacters(
        page: Int?,
        name: String?,
        status: CharacterStatus?,
        gender: Gender?
    ): Characters {
        val response = characterService.getCharacters(
            page = page,
            name = name,
            status = status,
            gender = gender
        ).getOrThrow()
        val characters = CharacterMapper.fromNetwork(response)
        _charactersCount.value = characters.info.count
        return characters
    }

    companion object {

        private const val INITIAL_CHARACTERS_COUNT = 0
    }
}