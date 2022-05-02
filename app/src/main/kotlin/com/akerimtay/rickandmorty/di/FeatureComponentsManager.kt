package com.akerimtay.rickandmorty.di

import com.akerimtay.rickandmorty.App
import com.akerimtay.rickandmorty.di.dependencies.CharactersComponentDependency

internal object FeatureComponentsManager {

    fun init(app: App) {
        CharactersComponentDependency.create(app)
    }
}