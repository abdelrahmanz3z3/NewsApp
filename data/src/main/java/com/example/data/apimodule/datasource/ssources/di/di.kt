package com.example.data.apimodule.datasource.ssources.di

import com.example.data.apimodule.datasource.ssources.contracts.SourcesDataSource
import com.example.data.apimodule.datasource.ssources.implentaion.SourcesDataSourceImpl
import com.example.data.apimodule.repository.SourcesRepositoryImpl
import com.example.domain.repository.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {

    @Binds
    abstract fun bindSourcesDataSource(sourcesDataSourceImpl: SourcesDataSourceImpl): SourcesDataSource

    @Binds
    abstract fun bindSourcesRepository(sourcesRepositoryImpl: SourcesRepositoryImpl): SourcesRepository
}