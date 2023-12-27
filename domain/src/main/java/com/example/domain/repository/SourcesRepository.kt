package com.example.domain.repository

import com.example.domain.common.ResultWrapper
import com.example.domain.model.Sources
import kotlinx.coroutines.flow.Flow

interface SourcesRepository {
    suspend fun getSources(category: String): Flow<ResultWrapper<List<Sources?>?>>
}