package com.akerimtay.rickandmorty.episode

import com.akerimtay.rickandmorty.core.common.model.Character
import com.akerimtay.rickandmorty.episode.di.EpisodeComponent
import com.akerimtay.rickandmorty.episode.domain.EpisodeRepository
import com.akerimtay.rickandmorty.moduleinjector.BaseFeatureAPI
import com.akerimtay.rickandmorty.moduleinjector.BaseFeatureDependencies
import com.akerimtay.rickandmorty.moduleinjector.ComponentHolder
import com.akerimtay.rickandmorty.moduleinjector.ComponentHolderDelegate
import retrofit2.Retrofit

object EpisodeComponentHolder : ComponentHolder<EpisodeFeatureApi, EpisodeFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<
        EpisodeFeatureApi,
        EpisodeFeatureDependencies,
        EpisodeComponent> { dependencies -> EpisodeComponent.initAndGet(dependencies) }

    internal fun getComponent(): EpisodeComponent = componentHolderDelegate.getComponentImpl()

    override var dependencyProvider: (() -> EpisodeFeatureDependencies)? by componentHolderDelegate::dependencyProvider

    override fun get(): EpisodeFeatureApi = componentHolderDelegate.get()
}

interface EpisodeFeatureDependencies : BaseFeatureDependencies {

    val retrofit: Retrofit
    val characterContract: CharacterContract
}

interface EpisodeFeatureApi : BaseFeatureAPI {

    val episodeRepository: EpisodeRepository
}

interface CharacterContract {

    suspend fun getCharacters(): List<Character>
}