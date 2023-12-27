package com.example.data.apimodule.repository

import com.example.data.apimodule.datasource.ssources.contracts.SourcesDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.model.Sources
import com.example.domain.repository.SourcesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(private val source: SourcesDataSource) :
    SourcesRepository {
    override suspend fun getSources(category: String): Flow<ResultWrapper<List<Sources?>?>> {
        val data = source.getSources(category)
        return data
    }
}