package com.akerimtay.rickandmorty.characters.di

import com.akerimtay.rickandmorty.characters.CharacterFeatureApi
import com.akerimtay.rickandmorty.characters.CharacterFeatureDependencies
import dagger.Component

@Component(dependencies = [CharacterFeatureDependencies::class], modules = [CharacterModule::class])
internal interface CharacterComponent : CharacterFeatureApi {
    companion object {
        fun initAndGet(dependencies: CharacterFeatureDependencies): CharacterComponent =
            DaggerCharacterComponent.builder()
                .characterFeatureDependencies(dependencies)
                .build()
    }
}