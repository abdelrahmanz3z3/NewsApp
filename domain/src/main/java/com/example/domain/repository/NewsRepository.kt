package com.example.domain.repository

import com.example.domain.common.ResultWrapper
import com.example.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(source: String, q: String?): Flow<ResultWrapper<List<News?>?>>
}