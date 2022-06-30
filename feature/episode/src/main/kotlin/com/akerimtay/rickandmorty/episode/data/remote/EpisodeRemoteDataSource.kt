package com.akerimtay.rickandmorty.episode.data.remote

internal interface EpisodeRemoteDataSource {

    suspend fun getEpisodes(): List<Any>
}