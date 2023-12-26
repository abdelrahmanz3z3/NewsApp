package com.example.domain.repository

import com.example.domain.model.News

interface NewsRepository {
    suspend fun getNews(source: String, q: String?): List<News?>?
}