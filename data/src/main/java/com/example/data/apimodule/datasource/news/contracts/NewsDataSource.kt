package com.example.data.apimodule.datasource.news.contracts

import com.example.domain.model.News

interface NewsDataSource {
    suspend fun getNews(source: String, q: String?): List<News?>?
}