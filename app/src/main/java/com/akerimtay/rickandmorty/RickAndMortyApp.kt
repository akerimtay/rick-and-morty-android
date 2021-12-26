package com.akerimtay.rickandmorty

import android.app.Application
import com.akerimtay.rickandmorty.timber.TimberManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RickAndMortyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        TimberManager.init()
    }
}