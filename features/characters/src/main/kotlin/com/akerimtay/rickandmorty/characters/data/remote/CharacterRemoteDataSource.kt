package com.akerimtay.rickandmorty.characters.data.remote

import com.akerimtay.rickandmorty.model.CharacterStatus
import com.akerimtay.rickandmorty.model.Characters
import com.akerimtay.rickandmorty.model.Gender

internal interface CharacterRemoteDataSource {

    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        status: CharacterStatus? = null,
        gender: Gender? = null
    ): Characters
}