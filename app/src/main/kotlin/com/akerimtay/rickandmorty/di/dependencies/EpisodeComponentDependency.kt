package com.akerimtay.rickandmorty.di.dependencies

import com.akerimtay.rickandmorty.App
import com.akerimtay.rickandmorty.characters.CharacterComponentHolder
import com.akerimtay.rickandmorty.characters.CharacterFeatureApi
import com.akerimtay.rickandmorty.entity.Character
import com.akerimtay.rickandmorty.episode.CharacterContract
import com.akerimtay.rickandmorty.episode.EpisodeComponentHolder
import com.akerimtay.rickandmorty.episode.EpisodeFeatureDependencies
import com.akerimtay.rickandmorty.moduleinjector.BaseDependencyHolder
import com.akerimtay.rickandmorty.moduleinjector.BaseFeatureDependencies
import com.akerimtay.rickandmorty.moduleinjector.DependencyHolder1

object EpisodeComponentDependency : InjectionComponentDependency {

    override fun create(app: App) {
        EpisodeComponentHolder.dependencyProvider = {
            class EpisodeComponentDependencyHolder(
                override val block: (
                    BaseDependencyHolder<EpisodeFeatureDependencies>, CharacterFeatureApi
                ) -> EpisodeFeatureDependencies
            ) : DependencyHolder1<CharacterFeatureApi, EpisodeFeatureDependencies>(
                api1 = CharacterComponentHolder.get()
            )

            EpisodeComponentDependencyHolder { dependencies, characterFeatureApi ->
                object : EpisodeFeatureDependencies {
                    override val characterContract: CharacterContract = object : CharacterContract {
                        override suspend fun getCharacters(): List<Character> {
                            return characterFeatureApi.characterRepository.getCharacters()
                        }
                    }

                    override val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies> = dependencies
                }
            }.dependencies
        }
    }
}