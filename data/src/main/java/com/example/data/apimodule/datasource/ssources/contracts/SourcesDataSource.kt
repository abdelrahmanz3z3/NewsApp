package com.example.data.apimodule.datasource.ssources.contracts

import com.example.domain.common.ResultWrapper
import com.example.domain.model.Sources
import kotlinx.coroutines.flow.Flow

interface SourcesDataSource {
    suspend fun getSources(category: String): Flow<ResultWrapper<List<Sources?>?>>
}