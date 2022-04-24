package com.akerimtay.rickandmorty

import android.app.Application
import android.content.Context
import com.akerimtay.rickandmorty.core.di.AppComponent
import com.akerimtay.rickandmorty.core.di.AppComponentDependencies
import com.akerimtay.rickandmorty.core.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(
                context = this,
                dependencies = object : AppComponentDependencies {}
            )
        appComponent.inject(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }