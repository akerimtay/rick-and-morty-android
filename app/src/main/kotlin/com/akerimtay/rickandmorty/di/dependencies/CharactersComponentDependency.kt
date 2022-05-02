package com.akerimtay.rickandmorty.di.dependencies

import com.akerimtay.rickandmorty.App
import com.akerimtay.rickandmorty.characters.CharactersComponentHolder
import com.akerimtay.rickandmorty.characters.CharactersFeatureDependencies
import com.akerimtay.rickandmorty.moduleinjector.BaseDependencyHolder
import com.akerimtay.rickandmorty.moduleinjector.BaseFeatureDependencies
import com.akerimtay.rickandmorty.moduleinjector.DependencyHolder
import retrofit2.Retrofit

object CharactersComponentDependency : InjectionComponentDependency {

    override fun create(app: App) {
        CharactersComponentHolder.dependencyProvider = {
            class CharactersComponentDependencyHolder(
                override val block: (BaseDependencyHolder<CharactersFeatureDependencies>) -> CharactersFeatureDependencies
            ) : DependencyHolder<CharactersFeatureDependencies>()

            CharactersComponentDependencyHolder { dependencies ->
                object : CharactersFeatureDependencies {
                    override val retrofit: Retrofit = app.retrofit
                    override val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies> = dependencies
                }
            }.dependencies
        }
    }
}