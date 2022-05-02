package com.akerimtay.rickandmorty.di

import android.content.Context
import com.akerimtay.rickandmorty.BuildConfig
import com.akerimtay.rickandmorty.core.presentation.util.extensions.applyIf
import com.akerimtay.rickandmorty.model.CharacterStatus
import com.akerimtay.rickandmorty.model.Gender
import com.akerimtay.rickandmorty.network.NetworkResponseAdapterFactory
import com.akerimtay.rickandmorty.network.adapter.CharacterStatusTypeAdapter
import com.akerimtay.rickandmorty.network.adapter.DateTypeAdapter
import com.akerimtay.rickandmorty.network.adapter.GenderTypeAdapter
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import java.util.Date
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .applyIf(BuildConfig.DEBUG) { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun provideChuckerInterceptor(context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context = context)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateTypeAdapter())
            .registerTypeAdapter(Gender::class.java, GenderTypeAdapter())
            .registerTypeAdapter(CharacterStatus::class.java, CharacterStatusTypeAdapter())
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}