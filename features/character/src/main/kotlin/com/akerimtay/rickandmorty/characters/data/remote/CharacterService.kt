package com.akerimtay.rickandmorty.characters.data.remote

import com.akerimtay.rickandmorty.characters.data.remote.model.CharactersResponse
import com.akerimtay.rickandmorty.core.common.network.BaseResponse
import com.akerimtay.rickandmorty.entity.CharacterStatus
import com.akerimtay.rickandmorty.entity.Gender
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CharacterService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status: CharacterStatus? = null,
        @Query("gender") gender: Gender? = null
    ): BaseResponse<CharactersResponse>
}