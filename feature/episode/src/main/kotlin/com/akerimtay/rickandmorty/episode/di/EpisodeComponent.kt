package com.akerimtay.rickandmorty.episode.di

import com.akerimtay.rickandmorty.episode.EpisodeFeatureApi
import com.akerimtay.rickandmorty.episode.EpisodeFeatureDependencies
import com.akerimtay.rickandmorty.episode.presentation.EpisodesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [EpisodeFeatureDependencies::class], modules = [EpisodeModule::class])
internal interface EpisodeComponent : EpisodeFeatureApi {

    fun inject(fragment: EpisodesFragment)

    companion object {

        fun initAndGet(dependencies: EpisodeFeatureDependencies): EpisodeComponent =
            DaggerEpisodeComponent.builder()
                .episodeFeatureDependencies(dependencies)
                .build()
    }
}