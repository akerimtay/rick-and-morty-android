package com.akerimtay.rickandmorty.characters.data.remote

import com.akerimtay.rickandmorty.entity.CharacterStatus
import com.akerimtay.rickandmorty.entity.Characters
import com.akerimtay.rickandmorty.entity.Gender
import kotlinx.coroutines.flow.Flow

internal interface CharacterRemoteDataSource {

    val charactersCount: Flow<Int>

    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        status: CharacterStatus? = null,
        gender: Gender? = null
    ): Characters
}