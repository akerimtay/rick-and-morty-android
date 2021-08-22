package com.akerimtay.rickandmorty.character.domain

import com.akerimtay.rickandmorty.character.model.Character
import com.akerimtay.rickandmorty.character.model.CharacterStatus
import com.akerimtay.rickandmorty.character.model.Gender
import com.akerimtay.rickandmorty.common.BasePagedModel

interface CharacterRemoteGateway {
    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        status: CharacterStatus? = null,
        gender: Gender? = null
    ): BasePagedModel<List<Character>>

    suspend fun getCharacterById(characterId: Int): Character
}