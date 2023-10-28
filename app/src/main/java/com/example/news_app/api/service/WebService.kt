package com.example.news_app.api.service

import com.example.news_app.api.model.newsresponse.NewsResponse
import com.example.news_app.api.model.sourcesresponse.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("v2/top-headlines/sources")
    suspend fun getSourceResponse(
        @Query("apiKey")
        apiKey: String = "0fb12c84804e45338bfa0fa9e27ddf49",
        @Query("category")
        category: String

    ): SourceResponse

    @GET("v2/everything")
    suspend fun getNewsResponses(
        @Query("apiKey")
        apiKey: String = "0fb12c84804e45338bfa0fa9e27ddf49",
        @Query("q")
        q: String?,
        @Query("sources")
        sources: String?,
    ): NewsResponse

}