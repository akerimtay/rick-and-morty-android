package com.akerimtay.rickandmorty.episode.data.remote

import com.akerimtay.rickandmorty.episode.data.remote.model.EpisodesResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface EpisodeService {

    @GET("episode")
    suspend fun getEpisodes(@Query("episode") episode: String): EpisodesResponse
}