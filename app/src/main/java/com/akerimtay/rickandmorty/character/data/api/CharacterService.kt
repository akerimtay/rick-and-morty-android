package com.akerimtay.rickandmorty.character.data.api

import com.akerimtay.rickandmorty.character.model.CharacterStatus
import com.akerimtay.rickandmorty.character.model.Gender
import com.akerimtay.rickandmorty.network.BasePagedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("character")
    suspend fun getCharactersAsync(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status: CharacterStatus? = null,
        @Query("gender") gender: Gender? = null
    ): Response<BasePagedResponse<List<CharacterResponse>>>

    @GET("character/{character_id}")
    suspend fun getCharacterAsync(
        @Path("character_id") characterId: Int
    ): Response<CharacterResponse>
}