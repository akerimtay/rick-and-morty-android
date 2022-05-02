package com.akerimtay.rickandmorty.characters

import com.akerimtay.rickandmorty.characters.di.CharacterComponent
import com.akerimtay.rickandmorty.moduleinjector.BaseFeatureAPI
import com.akerimtay.rickandmorty.moduleinjector.BaseFeatureDependencies
import com.akerimtay.rickandmorty.moduleinjector.ComponentHolder
import com.akerimtay.rickandmorty.moduleinjector.ComponentHolderDelegate
import retrofit2.Retrofit

object CharacterComponentHolder : ComponentHolder<CharacterFeatureApi, CharacterFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<
        CharacterFeatureApi,
        CharacterFeatureDependencies,
        CharacterComponent> { CharacterComponent.initAndGet(it) }

    internal fun getComponent(): CharacterComponent = componentHolderDelegate.getComponentImpl()

    override var dependencyProvider:
        (() -> CharacterFeatureDependencies)? by componentHolderDelegate::dependencyProvider

    override fun get(): CharacterFeatureApi = componentHolderDelegate.get()
}

interface CharacterFeatureDependencies : BaseFeatureDependencies {

    val retrofit: Retrofit
}

interface CharacterFeatureApi : BaseFeatureAPI