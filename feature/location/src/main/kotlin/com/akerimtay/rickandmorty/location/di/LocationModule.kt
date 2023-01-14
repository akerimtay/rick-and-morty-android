package com.akerimtay.rickandmorty.location.di

import com.akerimtay.rickandmorty.location.data.remote.LocationRemoteDataSource
import com.akerimtay.rickandmorty.location.data.remote.LocationRemoteDataSourceImpl
import com.akerimtay.rickandmorty.location.data.remote.LocationService
import com.akerimtay.rickandmorty.location.domain.LocationRepository
import com.akerimtay.rickandmorty.location.data.repository.LocationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
internal interface LocationModule {

    @Binds
    @Singleton
    fun bindLocationRepository(locationRepositoryImpl: LocationRepositoryImpl): LocationRepository

    @Binds
    @Singleton
    fun bindLocationDataSource(locationRemoteDataSourceImpl: LocationRemoteDataSourceImpl): LocationRemoteDataSource

    companion object {

        @Provides
        @Singleton
        fun provideLocationService(retrofit: Retrofit): LocationService {
            return retrofit.create(LocationService::class.java)
        }
    }
}