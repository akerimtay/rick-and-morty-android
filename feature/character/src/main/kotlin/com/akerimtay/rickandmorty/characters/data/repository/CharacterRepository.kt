package com.akerimtay.rickandmorty.characters.data.repository

import androidx.paging.PagingData
import com.akerimtay.rickandmorty.core.common.model.Character
import com.akerimtay.rickandmorty.core.common.model.CharacterStatus
import com.akerimtay.rickandmorty.core.common.model.Gender
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    val charactersCount: Flow<Int>

    fun getAsPagingData(
        name: String? = null,
        status: CharacterStatus? = null,
        gender: Gender? = null,
    ): Flow<PagingData<Character>>

    suspend fun getCharacters(): List<Character>
}