package com.akerimtay.rickandmorty.di

import com.akerimtay.rickandmorty.App
import com.akerimtay.rickandmorty.di.dependencies.CharacterComponentDependency
import com.akerimtay.rickandmorty.di.dependencies.EpisodeComponentDependency

internal object FeatureComponentsManager {

    fun init(app: App) {
        CharacterComponentDependency.create(app)
        EpisodeComponentDependency.create(app)
    }
}