package com.example.data.apimodule.model.news

import com.example.data.apimodule.model.newsources.SourcesItem
import com.example.domain.model.News
import com.google.gson.annotations.SerializedName

data class ArticlesItem(


    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("source")
    val source: SourcesItem? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("content")
    val content: String? = null
) {
    fun toNews(): News {
        return News(
            publishedAt = publishedAt,
            author = author,
            urlToImage = urlToImage,
            description = description,
            source = source?.toSources(),
            title = title,
            url = url,
            content = content,
        )
    }
}