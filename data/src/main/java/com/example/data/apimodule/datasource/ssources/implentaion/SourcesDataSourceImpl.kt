package com.example.data.apimodule.datasource.ssources.implentaion

import com.example.data.apimodule.datasource.ssources.contracts.SourcesDataSource
import com.example.data.apimodule.webservice.WebService
import com.example.domain.model.Sources
import javax.inject.Inject

class SourcesDataSourceImpl @Inject constructor(private val webService: WebService) :
    SourcesDataSource {
    override suspend fun getSources(category: String): List<Sources?>? {
        val data = webService.getSources(category = category)
        return data.sources?.map {
            it?.toSources()
        }
    }
}