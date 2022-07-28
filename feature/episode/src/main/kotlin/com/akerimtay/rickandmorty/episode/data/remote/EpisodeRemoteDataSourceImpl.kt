package com.akerimtay.rickandmorty.episode.data.remote

import com.akerimtay.rickandmorty.core.common.model.Episodes
import com.akerimtay.rickandmorty.episode.data.EpisodeMapper
import javax.inject.Inject

internal class EpisodeRemoteDataSourceImpl @Inject constructor(
    private val episodeService: EpisodeService
) : EpisodeRemoteDataSource {

    override suspend fun getEpisodes(season: String): Episodes {
        val response = episodeService.getEpisodes(episode = season)
        return EpisodeMapper.fromNetwork(response)
    }
}