package com.akerimtay.data.db.gateway

import com.akerimtay.data.db.dao.CharacterDao
import com.akerimtay.data.mapper.CharacterMapper
import com.akerimtay.domain.model.Character
import javax.inject.Inject

class CharacterLocalGateway @Inject constructor(
    private val characterDao: CharacterDao
) {
    suspend fun save(character: Character) {
        characterDao.save(CharacterMapper.toDatabase(character))
    }

    suspend fun getById(id: Int): Character? = characterDao.getById(id)?.let { CharacterMapper.fromDatabase(it) }
}