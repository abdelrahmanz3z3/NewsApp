package com.example.news_app.api.manager

import com.example.news_app.api.service.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    private var retInstance: Retrofit? = null
    private fun getInstance(): Retrofit {
        if (retInstance == null) {
            retInstance = Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retInstance!!
    }

    fun getApi(): WebService {
        return getInstance().create(WebService::class.java)
    }
}