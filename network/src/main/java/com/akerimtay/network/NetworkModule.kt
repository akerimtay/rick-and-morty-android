package com.akerimtay.network

import com.akerimtay.common.BuildConfig
import com.akerimtay.common.model.CharacterStatus
import com.akerimtay.common.model.Gender
import com.akerimtay.common.util.applyIf
import com.akerimtay.network.adapter.CharacterStatusTypeAdapter
import com.akerimtay.network.adapter.DateTypeAdapter
import com.akerimtay.network.adapter.GenderTypeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Date
import java.util.concurrent.TimeUnit
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
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().applyIf(BuildConfig.DEBUG) { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateTypeAdapter())
            .registerTypeAdapter(Gender::class.java, GenderTypeAdapter())
            .registerTypeAdapter(CharacterStatus::class.java, CharacterStatusTypeAdapter())
            .create()

    @Provides
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
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
}