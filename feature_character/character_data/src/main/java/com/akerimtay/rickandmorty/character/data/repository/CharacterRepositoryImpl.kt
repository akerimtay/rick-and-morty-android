package com.akerimtay.rickandmorty.character.data.repository

import com.akerimtay.rickandmorty.character.data.local.CharacterLocalGateway
import com.akerimtay.rickandmorty.character.data.remote.CharacterRemoteGateway
import com.akerimtay.rickandmorty.character.domain.model.Character
import com.akerimtay.rickandmorty.character.domain.model.CharacterStatus
import com.akerimtay.rickandmorty.character.domain.model.Characters
import com.akerimtay.rickandmorty.character.domain.model.Gender
import com.akerimtay.rickandmorty.character.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteGateway: CharacterRemoteGateway,
    private val characterLocalGateway: CharacterLocalGateway
) : CharacterRepository {
    override suspend fun getCharacters(
        page: Int?,
        name: String?,
        characterStatus: CharacterStatus?,
        gender: Gender?
    ): Characters = characterRemoteGateway.getCharacters().also { characters ->
        characters.results.forEach { characterLocalGateway.save(it) }
    }

    override suspend fun getCharacterById(characterId: Int): Character =
        characterRemoteGateway.getCharacterById(characterId)
}