package com.akerimtay.rickandmorty.characters.data.remote

import com.akerimtay.rickandmorty.characters.data.CharacterMapper
import com.akerimtay.rickandmorty.core.common.model.Character
import com.akerimtay.rickandmorty.core.common.model.CharacterStatus
import com.akerimtay.rickandmorty.core.common.model.Gender
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class CharacterRemoteDataSourceImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterRemoteDataSource {

    private val _charactersCount = MutableStateFlow(INITIAL_COUNT)
    override val charactersCount: Flow<Int> = _charactersCount

    override suspend fun getCharacters(
        page: Int?,
        name: String?,
        status: CharacterStatus?,
        gender: Gender?
    ): List<Character> {
        val response = characterService.getCharacters(
            page = page,
            name = name,
            status = status,
            gender = gender
        )
        val characters = CharacterMapper.fromNetwork(response)
        _charactersCount.value = characters.info.count
        return characters.results
    }

    companion object {

        private const val INITIAL_COUNT = 0
    }
}