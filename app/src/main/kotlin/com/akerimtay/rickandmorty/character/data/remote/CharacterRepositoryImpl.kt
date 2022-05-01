package com.akerimtay.rickandmorty.character.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.akerimtay.rickandmorty.character.domain.model.Character
import com.akerimtay.rickandmorty.character.domain.model.CharacterStatus
import com.akerimtay.rickandmorty.character.domain.model.Gender
import com.akerimtay.rickandmorty.character.domain.repository.CharacterRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

private const val DEFAULT_PAGE_SIZE = 20

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterRepository {

    override fun getCharactersPagingData(
        name: String?,
        status: CharacterStatus?,
        gender: Gender?
    ): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
            pagingSourceFactory = {
                CharacterPagingSource(
                    characterService = characterService,
                    name = name,
                    status = status,
                    gender = gender
                )
            }
        ).flow
    }
}