package com.akerimtay.rickandmorty.character.data.remote

import com.akerimtay.rickandmorty.character.data.remote.model.CharacterResponse
import com.akerimtay.rickandmorty.character.data.remote.model.CharactersResponse
import com.akerimtay.rickandmorty.character.domain.model.CharacterStatus
import com.akerimtay.rickandmorty.character.domain.model.Gender
import com.akerimtay.rickandmorty.common.base.network.BaseResponse
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
    ): BaseResponse<CharactersResponse>

    @GET("character/{character_id}")
    suspend fun getCharacterAsync(
        @Path("character_id") characterId: Int
    ): BaseResponse<CharacterResponse>
}