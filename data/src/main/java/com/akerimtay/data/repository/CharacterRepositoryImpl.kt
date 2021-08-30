package com.akerimtay.data.repository

import com.akerimtay.data.db.gateway.CharacterLocalGateway
import com.akerimtay.data.remote.gateway.CharacterRemoteGateway
import com.akerimtay.domain.model.Character
import com.akerimtay.domain.model.CharacterStatus
import com.akerimtay.domain.model.Characters
import com.akerimtay.domain.model.Gender
import com.akerimtay.domain.repository.CharacterRepository
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