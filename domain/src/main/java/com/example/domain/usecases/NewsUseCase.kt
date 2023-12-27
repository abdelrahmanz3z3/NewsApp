package com.example.domain.usecases

import com.example.domain.common.ResultWrapper
import com.example.domain.model.News
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun getNews(source: String, q: String?): Flow<ResultWrapper<List<News?>?>> {
        val data = newsRepository.getNews(source, q)
        return data
    }
}