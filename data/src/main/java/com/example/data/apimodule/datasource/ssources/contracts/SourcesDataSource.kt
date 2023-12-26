package com.example.data.apimodule.datasource.ssources.contracts

import com.example.domain.model.Sources

interface SourcesDataSource {
    suspend fun getSources(category: String): List<Sources?>?
}