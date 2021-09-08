package com.akerimtay.rickandmorty.character.data.di

import com.akerimtay.rickandmorty.character.data.remote.CharacterService
import com.akerimtay.rickandmorty.character.data.repository.CharacterRepositoryImpl
import com.akerimtay.rickandmorty.character.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CharacterDataModule {
    @Provides
    @Singleton
    fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(CharacterService::class.java)

    @Provides
    @Singleton
    fun provideCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository = characterRepositoryImpl
}