package com.example.data.apimodule.repository

import com.example.data.apimodule.datasource.news.contracts.NewsDataSource
import com.example.domain.common.ResultWrapper
import com.example.domain.model.News
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsDataSource: NewsDataSource) :
    NewsRepository {
    override suspend fun getNews(source: String, q: String?): Flow<ResultWrapper<List<News?>?>> {
        val data = newsDataSource.getNews(source = source, q = q)
        return data
    }
}