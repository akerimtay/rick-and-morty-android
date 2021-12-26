package com.akerimtay.rickandmorty.timber

import com.akerimtay.rickandmorty.BuildConfig
import timber.log.Timber

object TimberManager {

    fun init() {
        val tree = if (BuildConfig.DEBUG) {
            DevelopmentTree()
        } else {
            ProductionTree()
        }
        Timber.plant(tree)
    }
}