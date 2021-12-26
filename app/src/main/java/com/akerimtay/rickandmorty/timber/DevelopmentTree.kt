package com.akerimtay.rickandmorty.timber

import com.akerimtay.rickandmorty.BuildConfig
import timber.log.Timber

class DevelopmentTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String =
        "RickAndMortyApp-${BuildConfig.BUILD_TYPE}:${super.createStackElementTag(element)}:${element.lineNumber}"
}