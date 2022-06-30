package com.akerimtay.rickandmorty.episode.data.repository

import com.akerimtay.rickandmorty.episode.data.remote.EpisodeRemoteDataSource
import javax.inject.Inject

internal class EpisodeRepositoryImpl @Inject constructor(
    private val episodeRemoteDataSource: EpisodeRemoteDataSource
) : EpisodeRepository {

    override suspend fun getEpisodes(): List<Any> {
        return episodeRemoteDataSource.getEpisodes()
    }
}