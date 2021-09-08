package com.akerimtay.rickandmorty.character.domain.repository

import com.akerimtay.rickandmorty.character.domain.model.Character
import com.akerimtay.rickandmorty.character.domain.model.CharacterStatus
import com.akerimtay.rickandmorty.character.domain.model.Characters
import com.akerimtay.rickandmorty.character.domain.model.Gender

interface CharacterRepository {
    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        characterStatus: CharacterStatus? = null,
        gender: Gender? = null
    ): Characters

    suspend fun getCharacterById(characterId: Int): Character
}