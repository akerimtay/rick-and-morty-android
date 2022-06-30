package com.akerimtay.rickandmorty.episode.data.repository

interface EpisodeRepository {

    suspend fun getEpisodes(): List<Any>
}