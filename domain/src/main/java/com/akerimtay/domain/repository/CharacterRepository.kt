package com.akerimtay.domain.repository

import com.akerimtay.domain.model.Character
import com.akerimtay.domain.model.CharacterStatus
import com.akerimtay.domain.model.Characters
import com.akerimtay.domain.model.Gender

interface CharacterRepository {
    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        characterStatus: CharacterStatus? = null,
        gender: Gender? = null
    ): Characters

    suspend fun getCharacterById(characterId: Int): Character
}