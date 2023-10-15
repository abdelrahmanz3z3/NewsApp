package com.example.news_app.api.model.sourcesresponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceResponse(

    @field:SerializedName("sources")
    val sources: List<SourcesItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("message")
    val message: String? = null
) : Parcelable