package com.akerimtay.rickandmorty.timber

import android.util.Log
import timber.log.Timber

class ProductionTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        if (throwable != null && priority == Log.ERROR) {
            //TODO firebase crashlytics
        }
    }
}