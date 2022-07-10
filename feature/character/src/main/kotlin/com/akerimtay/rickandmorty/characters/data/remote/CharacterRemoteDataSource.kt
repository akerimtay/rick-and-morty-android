package com.akerimtay.rickandmorty.characters.data.remote

import com.akerimtay.rickandmorty.core.common.model.Character
import com.akerimtay.rickandmorty.core.common.model.CharacterStatus
import com.akerimtay.rickandmorty.core.common.model.Gender
import kotlinx.coroutines.flow.Flow

internal interface CharacterRemoteDataSource {

    val charactersCount: Flow<Int>

    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        status: CharacterStatus? = null,
        gender: Gender? = null
    ): List<Character>
}