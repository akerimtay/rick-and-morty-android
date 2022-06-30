package com.akerimtay.rickandmorty

import android.app.Application
import com.akerimtay.rickandmorty.di.AppComponent
import com.akerimtay.rickandmorty.di.AppComponentDependencies
import com.akerimtay.rickandmorty.di.DaggerAppComponent
import com.akerimtay.rickandmorty.di.FeatureComponentsManager
import javax.inject.Inject
import retrofit2.Retrofit
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent

    @Inject
    internal lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(
                context = this,
                dependencies = object : AppComponentDependencies {}
            )
        appComponent.inject(this)

        FeatureComponentsManager.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}