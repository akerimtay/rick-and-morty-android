package com.akerimtay.rickandmorty.characters.di

import com.akerimtay.rickandmorty.characters.CharactersFeatureApi
import com.akerimtay.rickandmorty.characters.CharactersFeatureDependencies
import dagger.Component

@Component(dependencies = [CharactersFeatureDependencies::class], modules = [CharactersModule::class])
internal interface CharactersComponent : CharactersFeatureApi {
    companion object {
        fun initAndGet(dependencies: CharactersFeatureDependencies): CharactersComponent =
            DaggerCharactersComponent.builder()
                .charactersFeatureDependencies(dependencies)
                .build()
    }
}