package com.akerimtay.rickandmorty.characters.data.repository

import androidx.paging.PagingData
import com.akerimtay.rickandmorty.model.Character
import com.akerimtay.rickandmorty.model.CharacterStatus
import com.akerimtay.rickandmorty.model.Gender
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getAsPagingData(
        pageSize: Int,
        name: String? = null,
        status: CharacterStatus? = null,
        gender: Gender? = null,
    ): Flow<PagingData<Character>>
}