package com.example.news_app.api.model.newsresponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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

) : Parcelable