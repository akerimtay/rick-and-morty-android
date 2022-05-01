package com.akerimtay.rickandmorty.character.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.akerimtay.rickandmorty.character.domain.model.Character
import com.akerimtay.rickandmorty.character.domain.model.CharacterStatus
import com.akerimtay.rickandmorty.character.domain.model.Gender
import java.io.IOException
import retrofit2.HttpException

private const val DEFAULT_PAGE_INDEX = 1

class CharacterPagingSource(
    private val characterService: CharacterService,
    private val name: String? = null,
    private val status: CharacterStatus? = null,
    private val gender: Gender? = null
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val networkResponse = characterService.getCharactersAsync(
                page = page,
                name = name,
                status = status,
                gender = gender
            )
//            val response = networkResponse.handleResponse()
//            val characters = CharacterMapper.fromNetwork(response = response).results

//            LoadResult.Page(
//                data = characters,
//                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
//                nextKey = if (characters.isEmpty()) null else page + 1
//            )
            TODO("Implement after refactor")
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}