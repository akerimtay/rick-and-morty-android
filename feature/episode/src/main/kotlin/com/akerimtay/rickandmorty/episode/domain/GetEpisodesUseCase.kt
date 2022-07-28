package com.akerimtay.rickandmorty.episode.domain

import com.akerimtay.rickandmorty.core.common.model.Episode
import com.akerimtay.rickandmorty.core.common.usecase.UseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class GetEpisodesUseCase @Inject constructor(
    private val episodeRepository: EpisodeRepository
) : UseCase<GetEpisodesUseCase.Param, List<Episode>>() {

    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Param): List<Episode> {
        return episodeRepository.getEpisodes(parameters.season)
    }

    data class Param(val season: String)
}