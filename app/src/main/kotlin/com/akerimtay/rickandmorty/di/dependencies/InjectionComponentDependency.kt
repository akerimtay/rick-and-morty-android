package com.akerimtay.rickandmorty.di.dependencies

import com.akerimtay.rickandmorty.App

interface InjectionComponentDependency {
    fun create(app: App)
}