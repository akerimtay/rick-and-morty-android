package com.akerimtay.rickandmorty.di.dependencies

import com.akerimtay.rickandmorty.App
import com.akerimtay.rickandmorty.characters.CharacterComponentHolder
import com.akerimtay.rickandmorty.characters.CharacterFeatureDependencies
import com.akerimtay.rickandmorty.moduleinjector.BaseDependencyHolder
import com.akerimtay.rickandmorty.moduleinjector.BaseFeatureDependencies
import com.akerimtay.rickandmorty.moduleinjector.DependencyHolder
import retrofit2.Retrofit

object CharacterComponentDependency : InjectionComponentDependency {

    override fun create(app: App) {
        CharacterComponentHolder.dependencyProvider = {
            class CharacterComponentDependencyHolder(
                override val block: (BaseDependencyHolder<CharacterFeatureDependencies>) -> CharacterFeatureDependencies
            ) : DependencyHolder<CharacterFeatureDependencies>()

            CharacterComponentDependencyHolder { dependencies ->
                object : CharacterFeatureDependencies {
                    override val retrofit: Retrofit = app.retrofit
                    override val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies> = dependencies
                }
            }.dependencies
        }
    }
}