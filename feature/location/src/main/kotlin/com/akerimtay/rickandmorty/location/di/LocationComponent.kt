package com.akerimtay.rickandmorty.location.di

import com.akerimtay.rickandmorty.location.LocationFeatureApi
import com.akerimtay.rickandmorty.location.LocationFeatureDependencies
import com.akerimtay.rickandmorty.location.presentation.list.LocationsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [LocationFeatureDependencies::class], modules = [LocationModule::class])
internal interface LocationComponent : LocationFeatureApi {

    fun inject(fragment: LocationsFragment)

    companion object {

        fun initAndGet(dependencies: LocationFeatureDependencies): LocationComponent =
            DaggerLocationComponent.builder()
                .locationFeatureDependencies(dependencies)
                .build()
    }
}