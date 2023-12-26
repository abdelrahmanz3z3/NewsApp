package com.example.news_app.ui.home.newsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.apimodule.model.news.NewsResponse
import com.example.data.apimodule.model.newsources.SourcesResponse
import com.example.domain.model.News
import com.example.domain.model.Sources
import com.example.domain.usecases.NewsUseCase
import com.example.domain.usecases.SourcesUseCase
import com.example.news_app.common.bindingclasses.ErrorContainer
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val sources: SourcesUseCase,
    private val news: NewsUseCase
) : ViewModel() {

    var showLoading = MutableLiveData<Boolean>()
    var sourcesData = MutableLiveData<List<Sources?>?>()
    var articlesData = MutableLiveData<List<News?>?>()
    var error = MutableLiveData<ErrorContainer>()

    fun getSources(cat: String) {

        showLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = sources.getSources(cat)
                sourcesData.postValue(response)
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val res = Gson().fromJson(errorBody, SourcesResponse::class.java)
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


    fun getNewsSources(id: String?, query: String? = null) {

        showLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = news.getNews(id ?: "", q = query)
                articlesData.postValue(response)

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