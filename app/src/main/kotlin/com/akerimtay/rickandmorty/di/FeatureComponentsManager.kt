package com.akerimtay.rickandmorty.di

import com.akerimtay.rickandmorty.App
import com.akerimtay.rickandmorty.di.dependencies.CharacterComponentDependency

internal object FeatureComponentsManager {

    fun init(app: App) {
        CharacterComponentDependency.create(app)
    }
}