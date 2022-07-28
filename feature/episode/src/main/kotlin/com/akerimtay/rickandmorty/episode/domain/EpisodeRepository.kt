package com.akerimtay.rickandmorty.episode.domain

import com.akerimtay.rickandmorty.core.common.model.Episode

interface EpisodeRepository {

    suspend fun getEpisodes(season: String): List<Episode>
}