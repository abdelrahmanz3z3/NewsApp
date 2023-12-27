package com.example.domain.usecases

import com.example.domain.common.ResultWrapper
import com.example.domain.model.Sources
import com.example.domain.repository.SourcesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SourcesUseCase @Inject constructor(private val sourcesRepository: SourcesRepository) {

    suspend fun getSources(category: String): Flow<ResultWrapper<List<Sources?>?>> {
        val data = sourcesRepository.getSources(category)
        return data
    }

}