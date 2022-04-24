package com.akerimtay.rickandmorty.core.di

import android.content.Context
import com.akerimtay.rickandmorty.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppComponentDependencies::class],
    modules = [AppModule::class, NetworkModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context, dependencies: AppComponentDependencies): AppComponent
    }

    fun inject(app: App)
}

interface AppComponentDependencies