package com.akerimtay.rickandmorty.characters.di

import com.akerimtay.rickandmorty.characters.data.remote.CharacterRemoteDataSource
import com.akerimtay.rickandmorty.characters.data.remote.CharacterRemoteDataSourceImpl
import com.akerimtay.rickandmorty.characters.data.remote.CharacterService
import com.akerimtay.rickandmorty.characters.data.repository.CharacterRepository
import com.akerimtay.rickandmorty.characters.data.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
internal interface CharacterModule {

    @Binds
    @Singleton
    fun bindCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    @Singleton
    fun bindCharacterDataSource(characterRemoteDataSourceImpl: CharacterRemoteDataSourceImpl): CharacterRemoteDataSource

    companion object {

        @Provides
        @Singleton
        fun provideCharacterService(retrofit: Retrofit): CharacterService {
            return retrofit.create(CharacterService::class.java)
        }
    }
}