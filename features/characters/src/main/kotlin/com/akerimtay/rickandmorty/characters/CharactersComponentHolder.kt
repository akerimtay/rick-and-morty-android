package com.akerimtay.rickandmorty.characters

import com.akerimtay.rickandmorty.characters.di.CharactersComponent
import com.akerimtay.rickandmorty.moduleinjector.BaseFeatureAPI
import com.akerimtay.rickandmorty.moduleinjector.BaseFeatureDependencies
import com.akerimtay.rickandmorty.moduleinjector.ComponentHolder
import com.akerimtay.rickandmorty.moduleinjector.ComponentHolderDelegate
import retrofit2.Retrofit

object CharactersComponentHolder : ComponentHolder<CharactersFeatureApi, CharactersFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<
        CharactersFeatureApi,
        CharactersFeatureDependencies,
        CharactersComponent> { CharactersComponent.initAndGet(it) }

    internal fun getComponent(): CharactersComponent = componentHolderDelegate.getComponentImpl()

    override var dependencyProvider:
        (() -> CharactersFeatureDependencies)? by componentHolderDelegate::dependencyProvider

    override fun get(): CharactersFeatureApi = componentHolderDelegate.get()
}

interface CharactersFeatureDependencies : BaseFeatureDependencies {

    val retrofit: Retrofit
}

interface CharactersFeatureApi : BaseFeatureAPI