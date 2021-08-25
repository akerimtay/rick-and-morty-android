package com.akerimtay.rickandmorty.character.di

import com.akerimtay.rickandmorty.character.data.api.CharacterRestApi
import com.akerimtay.rickandmorty.character.data.api.CharacterService
import com.akerimtay.rickandmorty.character.domain.CharacterRemoteGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CharacterModule {
    @Provides
    @Singleton
    fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(CharacterService::class.java)

    @Provides
    @Singleton
    fun provideCharacterRemoteGateway(characterService: CharacterService): CharacterRemoteGateway =
        CharacterRestApi(characterService = characterService)
}