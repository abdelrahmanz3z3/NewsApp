package com.example.data.apimodule.datasource.news.di

import com.example.data.apimodule.datasource.news.contracts.NewsDataSource
import com.example.data.apimodule.datasource.news.implementation.NewsDataSourcesImpl
import com.example.data.apimodule.repository.NewsRepositoryImpl
import com.example.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {
    @Binds
    abstract fun bindNewsDataSource(newsDataSourcesImpl: NewsDataSourcesImpl): NewsDataSource

    @Binds
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}