package com.akerimtay.rickandmorty.character.data.local

import com.akerimtay.rickandmorty.character.data.mapper.CharacterMapper
import com.akerimtay.rickandmorty.character.domain.model.Character
import javax.inject.Inject

class CharacterLocalGateway @Inject constructor(
    private val characterDao: CharacterDao
) {
    suspend fun save(character: Character) {
        characterDao.save(CharacterMapper.toDatabase(character))
    }

    suspend fun getById(id: Int): Character? = characterDao.getById(id)?.let { CharacterMapper.fromDatabase(it) }
}