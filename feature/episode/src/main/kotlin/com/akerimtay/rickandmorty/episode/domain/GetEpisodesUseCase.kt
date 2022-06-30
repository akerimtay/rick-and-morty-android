package com.akerimtay.rickandmorty.episode.domain

import com.akerimtay.rickandmorty.core.common.usecase.UseCase
import com.akerimtay.rickandmorty.episode.data.repository.EpisodeRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class GetEpisodesUseCase @Inject constructor(
    private val episodeRepository: EpisodeRepository
) : UseCase<Unit, List<Any>>() {

    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Unit): List<Any> {
        return episodeRepository.getEpisodes()
    }
}