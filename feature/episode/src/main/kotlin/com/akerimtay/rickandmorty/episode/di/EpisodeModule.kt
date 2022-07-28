package com.akerimtay.rickandmorty.episode.di

import com.akerimtay.rickandmorty.episode.data.remote.EpisodeRemoteDataSource
import com.akerimtay.rickandmorty.episode.data.remote.EpisodeRemoteDataSourceImpl
import com.akerimtay.rickandmorty.episode.data.remote.EpisodeService
import com.akerimtay.rickandmorty.episode.data.repository.EpisodeRepositoryImpl
import com.akerimtay.rickandmorty.episode.domain.EpisodeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
internal interface EpisodeModule {

    @Binds
    @Singleton
    fun bindEpisodeRepository(episodeRepositoryImpl: EpisodeRepositoryImpl): EpisodeRepository

    @Binds
    @Singleton
    fun bindEpisodeRemoteDataSource(episodeRemoteDataSourceImpl: EpisodeRemoteDataSourceImpl): EpisodeRemoteDataSource

    companion object {

        @Provides
        @Singleton
        fun provideEpisodeService(retrofit: Retrofit): EpisodeService {
            return retrofit.create(EpisodeService::class.java)
        }
    }
}