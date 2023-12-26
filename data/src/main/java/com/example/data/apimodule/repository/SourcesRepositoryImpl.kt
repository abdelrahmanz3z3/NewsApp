package com.example.data.apimodule.repository

import com.example.data.apimodule.datasource.ssources.contracts.SourcesDataSource
import com.example.domain.model.Sources
import com.example.domain.repository.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(private val source: SourcesDataSource) :
    SourcesRepository {
    override suspend fun getSources(category: String): List<Sources?>? {
        val data = source.getSources(category)
        return data
    }
}