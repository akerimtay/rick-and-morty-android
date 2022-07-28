package com.akerimtay.rickandmorty.episode.data

import com.akerimtay.rickandmorty.core.common.model.Episode
import com.akerimtay.rickandmorty.core.common.model.Episodes
import com.akerimtay.rickandmorty.episode.data.remote.model.EpisodeResponse
import com.akerimtay.rickandmorty.episode.data.remote.model.EpisodesResponse

internal object EpisodeMapper {

    fun fromNetwork(response: EpisodesResponse): Episodes {
        return Episodes(
            info = Episodes.Info(
                count = response.infoResponse.count,
                pages = response.infoResponse.pages,
                next = response.infoResponse.next,
                prev = response.infoResponse.prev
            ),
            results = response.results.map { fromNetwork(it) }
        )
    }

    fun fromNetwork(response: EpisodeResponse): Episode {
        return Episode(
            id = response.id,
            name = response.name,
            date = response.date,
            code = response.code
        )
    }
}