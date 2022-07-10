package com.akerimtay.rickandmorty.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.akerimtay.rickandmorty.characters.data.paging.CharacterPagingSource
import com.akerimtay.rickandmorty.characters.data.remote.CharacterRemoteDataSource
import com.akerimtay.rickandmorty.core.common.model.Character
import com.akerimtay.rickandmorty.core.common.model.CharacterStatus
import com.akerimtay.rickandmorty.core.common.model.Gender
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {

    override val charactersCount: Flow<Int>
        get() = characterRemoteDataSource.charactersCount

    override fun getAsPagingData(
        name: String?,
        status: CharacterStatus?,
        gender: Gender?
    ): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
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

    override suspend fun getCharacters(): List<Character> {
        return emptyList()
    }

    companion object {

        private const val DEFAULT_PAGE_SIZE = 20
    }
}