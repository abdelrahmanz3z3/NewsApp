package com.example.data.apimodule.datasource.news.implementation

import com.example.data.apimodule.common.safeApiCall
import com.example.data.apimodule.datasource.news.contracts.NewsDataSource
import com.example.data.apimodule.webservice.WebService
import com.example.domain.common.ResultWrapper
import com.example.domain.model.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsDataSourcesImpl @Inject constructor(private val webService: WebService) : NewsDataSource {
    override suspend fun getNews(source: String, q: String?): Flow<ResultWrapper<List<News?>?>> {
        val data = safeApiCall {
            webService.getNews(source = source, q = q).articles?.map {
                it?.toNews()
            }
        }
        return data
    }
}