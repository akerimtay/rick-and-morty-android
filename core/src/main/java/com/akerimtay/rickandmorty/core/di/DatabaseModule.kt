package com.akerimtay.rickandmorty.core.di

import android.content.Context
import androidx.room.Room
import com.akerimtay.rickandmorty.character.data.local.CharacterDao
import com.akerimtay.rickandmorty.core.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "rick_and_morty.db"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideCharacterDao(database: AppDatabase): CharacterDao = database.characterDao()
}