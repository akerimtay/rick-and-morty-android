package com.akerimtay.rickandmorty.episode.data.remote

import com.akerimtay.rickandmorty.core.common.model.Episodes

internal interface EpisodeRemoteDataSource {

    suspend fun getEpisodes(season: String): Episodes
}