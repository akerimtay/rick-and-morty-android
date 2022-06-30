package com.akerimtay.rickandmorty.episode.di

import com.akerimtay.rickandmorty.episode.data.remote.EpisodeRemoteDataSource
import com.akerimtay.rickandmorty.episode.data.remote.EpisodeRemoteDataSourceImpl
import com.akerimtay.rickandmorty.episode.data.repository.EpisodeRepository
import com.akerimtay.rickandmorty.episode.data.repository.EpisodeRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface EpisodeModule {

    @Binds
    @Singleton
    fun bindEpisodeRepository(episodeRepositoryImpl: EpisodeRepositoryImpl): EpisodeRepository

    @Binds
    @Singleton
    fun bindEpisodeRemoteDataSource(episodeRemoteDataSourceImpl: EpisodeRemoteDataSourceImpl): EpisodeRemoteDataSource
}