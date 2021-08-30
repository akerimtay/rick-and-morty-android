package com.akerimtay.data.di

import com.akerimtay.common.BuildConfig
import com.akerimtay.common.util.applyIf
import com.akerimtay.data.remote.adapter.CharacterStatusTypeAdapter
import com.akerimtay.data.remote.adapter.DateTypeAdapter
import com.akerimtay.data.remote.adapter.GenderTypeAdapter
import com.akerimtay.data.remote.base.NetworkResponseAdapterFactory
import com.akerimtay.domain.model.CharacterStatus
import com.akerimtay.domain.model.Gender
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val DEFAULT_CONNECT_TIMEOUT = 15L
    private const val DEFAULT_READ_TIMEOUT = 15L
    private const val DEFAULT_WRITE_TIMEOUT = 15L
    private const val DEFAULT_PING_INTERVAL = 15L
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().applyIf(BuildConfig.DEBUG) { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateTypeAdapter())
            .registerTypeAdapter(Gender::class.java, GenderTypeAdapter())
            .registerTypeAdapter(CharacterStatus::class.java, CharacterStatusTypeAdapter())
            .create()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .pingInterval(DEFAULT_PING_INTERVAL, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
}