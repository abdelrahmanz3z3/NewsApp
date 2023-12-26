package com.example.data.apimodule.webservice

import com.example.data.apimodule.model.news.NewsResponse
import com.example.data.apimodule.model.newsources.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("apiKey")
        apiKey: String = "91dd52cc84bb43d185b795af26e09007",
        @Query("sources")
        source: String,
        @Query("q")
        q: String? = null
    ): NewsResponse

    @GET("v2/top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey")
        apiKey: String = "91dd52cc84bb43d185b795af26e09007",
        @Query("category")
        category: String
    ): SourcesResponse
}