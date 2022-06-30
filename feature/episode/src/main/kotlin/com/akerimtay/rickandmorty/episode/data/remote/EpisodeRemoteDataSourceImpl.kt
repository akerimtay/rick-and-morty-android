package com.akerimtay.rickandmorty.episode.data.remote

import javax.inject.Inject

internal class EpisodeRemoteDataSourceImpl @Inject constructor() : EpisodeRemoteDataSource {

    override suspend fun getEpisodes(): List<Any> {
        return emptyList()
    }
}