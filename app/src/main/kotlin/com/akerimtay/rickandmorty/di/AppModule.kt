package com.akerimtay.rickandmorty.di

import com.akerimtay.rickandmorty.character.data.remote.CharacterRepositoryImpl
import com.akerimtay.rickandmorty.character.data.remote.CharacterService
import com.akerimtay.rickandmorty.character.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
internal interface AppModule {

    @Binds
    @Singleton
    fun bindCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

    companion object {

        @Provides
        @Singleton
        fun provideCharacterService(retrofit: Retrofit): CharacterService {
            return retrofit.create(CharacterService::class.java)
        }
    }
}