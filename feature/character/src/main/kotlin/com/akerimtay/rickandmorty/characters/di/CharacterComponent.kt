package com.akerimtay.rickandmorty.characters.di

import com.akerimtay.rickandmorty.characters.CharacterFeatureApi
import com.akerimtay.rickandmorty.characters.CharacterFeatureDependencies
import com.akerimtay.rickandmorty.characters.presentation.list.CharactersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [CharacterFeatureDependencies::class], modules = [CharacterModule::class])
internal interface CharacterComponent : CharacterFeatureApi {

    fun inject(fragment: CharactersFragment)

    companion object {

        fun initAndGet(dependencies: CharacterFeatureDependencies): CharacterComponent =
            DaggerCharacterComponent.builder()
                .characterFeatureDependencies(dependencies)
                .build()
    }
}