package com.example.data.apimodule.datasource.news.contracts

import com.example.domain.common.ResultWrapper
import com.example.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {
    suspend fun getNews(source: String, q: String?): Flow<ResultWrapper<List<News?>?>>
}