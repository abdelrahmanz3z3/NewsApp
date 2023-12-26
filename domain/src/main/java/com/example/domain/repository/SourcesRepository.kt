package com.example.domain.repository

import com.example.domain.model.Sources

interface SourcesRepository {
    suspend fun getSources(category: String): List<Sources?>?
}