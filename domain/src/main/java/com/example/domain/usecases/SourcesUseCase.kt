package com.example.domain.usecases

import com.example.domain.model.Sources
import com.example.domain.repository.SourcesRepository
import javax.inject.Inject

class SourcesUseCase @Inject constructor(private val sourcesRepository: SourcesRepository) {

    suspend fun getSources(category: String): List<Sources?>? {
        val data = sourcesRepository.getSources(category)
        return data
    }

}