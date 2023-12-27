package com.example.data.apimodule.datasource.ssources.implentaion

import com.example.data.apimodule.common.safeApiCall
import com.example.data.apimodule.datasource.ssources.contracts.SourcesDataSource
import com.example.data.apimodule.webservice.WebService
import com.example.domain.common.ResultWrapper
import com.example.domain.model.Sources
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SourcesDataSourceImpl @Inject constructor(private val webService: WebService) :
    SourcesDataSource {
    override suspend fun getSources(category: String): Flow<ResultWrapper<List<Sources?>?>> {
        val data = safeApiCall {
            webService.getSources(category = category).sources?.map {
                it?.toSources()
            }
        }
        return data
    }
}