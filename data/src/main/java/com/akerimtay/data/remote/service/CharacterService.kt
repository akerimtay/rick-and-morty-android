package com.akerimtay.data.remote.service

import com.akerimtay.data.remote.base.BaseResponse
import com.akerimtay.data.remote.model.CharacterResponse
import com.akerimtay.data.remote.model.CharactersResponse
import com.akerimtay.domain.model.CharacterStatus
import com.akerimtay.domain.model.Gender
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