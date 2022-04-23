package com.akerimtay.rickandmorty.content.di

import com.akerimtay.rickandmorty.common.adapter.ContentAdapter
import com.akerimtay.rickandmorty.common.adapter.PagedContentAdapter
import com.akerimtay.rickandmorty.content.ItemContentType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ContentItemModule {

    @Provides
    fun provideContentAdapter(): ContentAdapter<ItemContentType> = ContentAdapter(ItemContentType.values())

    @Provides
    fun providePagedContentAdapter(): PagedContentAdapter<ItemContentType> =
        PagedContentAdapter(ItemContentType.values())
}