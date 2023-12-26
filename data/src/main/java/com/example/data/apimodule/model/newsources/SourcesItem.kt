package com.example.data.apimodule.model.newsources

import com.example.domain.model.Sources
import com.google.gson.annotations.SerializedName

data class SourcesItem(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("language")
    val language: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("url")
    val url: String? = null
) {
    fun toSources(): Sources {
        return Sources(
            country = country,
            name = name,
            description = description,
            language = language,
            id = id,
            category = category,
            url = url,
        )
    }

}