package com.akerimtay.navigation.di

import com.akerimtay.navigation.navigator.Navigator
import com.akerimtay.navigation.navigator.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigatorModule {
    @Binds
    abstract fun bindNavigator(navigator: NavigatorImpl): Navigator
}