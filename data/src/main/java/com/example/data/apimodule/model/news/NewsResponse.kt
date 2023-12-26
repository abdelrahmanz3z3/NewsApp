package com.example.data.apimodule.model.news

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("code")
    val code: String? = null,
    @field:SerializedName("message")
    val message: String? = null
)