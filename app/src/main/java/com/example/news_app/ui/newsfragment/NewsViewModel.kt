package com.example.news_app.ui.newsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.api.manager.ApiManager
import com.example.news_app.api.model.newsresponse.ArticlesItem
import com.example.news_app.api.model.newsresponse.NewsResponse
import com.example.news_app.api.model.sourcesresponse.SourceResponse
import com.example.news_app.api.model.sourcesresponse.SourcesItem
import com.example.news_app.ui.bindingclasses.ErrorContainer
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NewsViewModel : ViewModel() {

    var showLoading = MutableLiveData<Boolean>()
    var sourcesData = MutableLiveData<List<SourcesItem?>?>()
    var articlesData = MutableLiveData<List<ArticlesItem?>?>()
    var error = MutableLiveData<ErrorContainer>()

    fun getSources(cat: String) {
        showLoading.postValue(true)
        viewModelScope.launch {
            try {
                var response = ApiManager.getApi().getSourceResponse(category = cat)
                sourcesData.postValue(response.sources)
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val res = Gson().fromJson(errorBody, SourceResponse::class.java)
                error.postValue(ErrorContainer(res.message ?: "Something went wrong") {
                    getSources(
                        cat
                    )
                })
            } catch (e: Exception) {
                error.postValue(
                    ErrorContainer(
                        e.message ?: "Something went wrong"
                    ) { getSources(cat) })
            } finally {
                showLoading.postValue(false)
            }
        }
    }


    fun getNewsSources(id: String?, query: String?) {

        showLoading.postValue(true)
        viewModelScope.launch {
            try {
                var response = ApiManager.getApi().getNewsResponses(sources = id ?: "", q = query)
                articlesData.postValue(response.articles)

            } catch (e: HttpException) {
                val errorResponse = e.response()?.errorBody()?.string()
                val res = Gson().fromJson(errorResponse, NewsResponse::class.java)
                error.postValue(
                    ErrorContainer(
                        res.message ?: "Something went wrong"
                    ) { getNewsSources(id, query) })

            } catch (e: Exception) {
                error.postValue(ErrorContainer(e.message ?: "Something went wrong") {
                    getNewsSources(id, query)
                })
            } finally {
                showLoading.postValue(false)
            }

        }
    }


}