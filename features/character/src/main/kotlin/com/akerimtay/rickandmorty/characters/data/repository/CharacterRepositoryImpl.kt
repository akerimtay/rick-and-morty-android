package com.akerimtay.rickandmorty.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.akerimtay.rickandmorty.characters.data.paging.CharacterPagingSource
import com.akerimtay.rickandmorty.characters.data.remote.CharacterRemoteDataSource
import com.akerimtay.rickandmorty.entity.Character
import com.akerimtay.rickandmorty.entity.CharacterStatus
import com.akerimtay.rickandmorty.entity.Gender
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {

    override val charactersCount: Flow<Int> = characterRemoteDataSource.charactersCount

    override fun getAsPagingData(
        pageSize: Int,
        name: String?,
        status: CharacterStatus?,
        gender: Gender?
    ): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharacterPagingSource(
                    characterRemoteDataSource = characterRemoteDataSource,
                    name = name,
                    status = status,
                    gender = gender
                )
            }
        ).flow
    }
}