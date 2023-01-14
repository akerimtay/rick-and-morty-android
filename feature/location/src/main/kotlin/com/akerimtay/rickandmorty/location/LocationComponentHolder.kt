package com.akerimtay.rickandmorty.location

import com.akerimtay.rickandmorty.location.domain.LocationRepository
import com.akerimtay.rickandmorty.location.di.LocationComponent
import com.akerimtay.rickandmorty.moduleinjector.BaseFeatureAPI
import com.akerimtay.rickandmorty.moduleinjector.BaseFeatureDependencies
import com.akerimtay.rickandmorty.moduleinjector.ComponentHolder
import com.akerimtay.rickandmorty.moduleinjector.ComponentHolderDelegate
import retrofit2.Retrofit

object LocationComponentHolder : ComponentHolder<LocationFeatureApi, LocationFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<
        LocationFeatureApi,
        LocationFeatureDependencies,
        LocationComponent> { LocationComponent.initAndGet(it) }

    internal fun getComponent(): LocationComponent = componentHolderDelegate.getComponentImpl()

    override var dependencyProvider:
        (() -> LocationFeatureDependencies)? by componentHolderDelegate::dependencyProvider

    override fun get(): LocationFeatureApi = componentHolderDelegate.get()
}

interface LocationFeatureDependencies : BaseFeatureDependencies {

    val retrofit: Retrofit
}

interface LocationFeatureApi : BaseFeatureAPI {

    val locationRepository: LocationRepository
}