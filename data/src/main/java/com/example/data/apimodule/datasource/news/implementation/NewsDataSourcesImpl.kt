package com.example.data.apimodule.datasource.news.implementation

import com.example.data.apimodule.datasource.news.contracts.NewsDataSource
import com.example.data.apimodule.webservice.WebService
import com.example.domain.model.News
import javax.inject.Inject

class NewsDataSourcesImpl @Inject constructor(private val webService: WebService) : NewsDataSource {
    override suspend fun getNews(source: String, q: String?): List<News?>? {
        val data = webService.getNews(source = source, q = q)
        return data.articles?.map {
            it?.toNews()
        }
    }
}