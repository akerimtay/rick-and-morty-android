package com.akerimtay.rickandmorty.characters.data.remote

import com.akerimtay.rickandmorty.entity.CharacterStatus
import com.akerimtay.rickandmorty.entity.Characters
import com.akerimtay.rickandmorty.entity.Gender

internal interface CharacterRemoteDataSource {

    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        status: CharacterStatus? = null,
        gender: Gender? = null
    ): Characters
}