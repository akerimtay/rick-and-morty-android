package com.akerimtay.rickandmorty.di

import com.akerimtay.rickandmorty.App
import com.akerimtay.rickandmorty.di.dependencies.CharacterComponentDependency
import com.akerimtay.rickandmorty.di.dependencies.EpisodeComponentDependency
import com.akerimtay.rickandmorty.di.dependencies.LocationComponentDependency

internal object FeatureComponentsManager {

    fun init(app: App) {
        CharacterComponentDependency.create(app)
        LocationComponentDependency.create(app)
        EpisodeComponentDependency.create(app)
    }
}