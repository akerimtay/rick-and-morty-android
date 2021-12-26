package com.akerimtay.rickandmorty.character.domain.repository

import androidx.paging.PagingData
import com.akerimtay.rickandmorty.character.domain.model.Character
import com.akerimtay.rickandmorty.character.domain.model.CharacterStatus
import com.akerimtay.rickandmorty.character.domain.model.Gender
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharactersPagingData(
        name: String? = null,
        status: CharacterStatus? = null,
        gender: Gender? = null
    ): Flow<PagingData<Character>>
}