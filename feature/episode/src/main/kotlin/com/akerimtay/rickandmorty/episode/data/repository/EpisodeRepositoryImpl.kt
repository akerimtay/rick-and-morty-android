package com.akerimtay.rickandmorty.episode.data.repository

import com.akerimtay.rickandmorty.core.common.model.Episode
import com.akerimtay.rickandmorty.episode.data.remote.EpisodeRemoteDataSource
import com.akerimtay.rickandmorty.episode.domain.EpisodeRepository
import javax.inject.Inject

internal class EpisodeRepositoryImpl @Inject constructor(
    private val episodeRemoteDataSource: EpisodeRemoteDataSource
) : EpisodeRepository {

    override suspend fun getEpisodes(season: String): List<Episode> {
        return episodeRemoteDataSource.getEpisodes(season = season).results
    }
}