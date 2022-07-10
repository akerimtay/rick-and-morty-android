package com.akerimtay.rickandmorty.di.dependencies

import com.akerimtay.rickandmorty.App
import com.akerimtay.rickandmorty.location.LocationComponentHolder
import com.akerimtay.rickandmorty.location.LocationFeatureDependencies
import com.akerimtay.rickandmorty.moduleinjector.BaseDependencyHolder
import com.akerimtay.rickandmorty.moduleinjector.BaseFeatureDependencies
import com.akerimtay.rickandmorty.moduleinjector.DependencyHolder
import retrofit2.Retrofit

object LocationComponentDependency : InjectionComponentDependency {

    override fun create(app: App) {
        LocationComponentHolder.dependencyProvider = {
            class LocationComponentDependencyHolder(
                override val block: (BaseDependencyHolder<LocationFeatureDependencies>) -> LocationFeatureDependencies
            ) : DependencyHolder<LocationFeatureDependencies>()

            LocationComponentDependencyHolder { dependencies ->
                object : LocationFeatureDependencies {
                    override val retrofit: Retrofit = app.retrofit
                    override val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies> = dependencies
                }
            }.dependencies
        }
    }
}