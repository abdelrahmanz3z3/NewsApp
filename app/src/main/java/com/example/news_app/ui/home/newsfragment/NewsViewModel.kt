package com.example.news_app.ui.home.newsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.apimodule.model.news.NewsResponse
import com.example.data.apimodule.model.newsources.SourcesResponse
import com.example.domain.usecases.NewsUseCase
import com.example.domain.usecases.SourcesUseCase
import com.example.news_app.common.SingleLiveEvent
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
) : ViewModel(), NewsContract.ViewModel {

    private val _event = SingleLiveEvent<NewsContract.Event>()
    private val _state = MutableLiveData<NewsContract.State>()
    override val event: SingleLiveEvent<NewsContract.Event>
        get() = _event
    override val state: MutableLiveData<NewsContract.State>
        get() = _state

    override fun invokeAction(action: NewsContract.Action) {

        when (action) {
            is NewsContract.Action.LoadNews -> {
                getNewsSources(action.source, action.q)
            }

            is NewsContract.Action.LoadSources -> {
                getSources(action.category)
            }
        }
    }

    private fun getSources(cat: String) {

        _state.postValue(NewsContract.State.Loading("Loading..."))
        viewModelScope.launch {
            try {
                val response = sources.getSources(cat)
                _state.postValue(NewsContract.State.SourcesSuccess(response))
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val res = Gson().fromJson(errorBody, SourcesResponse::class.java)
                _state.postValue(
                    NewsContract.State.Error(
                        ErrorContainer(
                            res.message ?: "Something went wrong"
                        ) {
                            getSources(cat)
                        })
                )
            } catch (e: Exception) {
                _state.postValue(NewsContract.State.Error(ErrorContainer(
                    e.message ?: "Something went wrong"
                )
                { getSources(cat) }
                ))
            }
        }
    }


    private fun getNewsSources(id: String?, query: String? = null) {

        _state.postValue(NewsContract.State.Loading("Loading..."))
        viewModelScope.launch {
            try {
                val response = news.getNews(id ?: "", q = query)
                _state.postValue(NewsContract.State.NewsSuccess(response))

            } catch (e: HttpException) {
                val errorResponse = e.response()?.errorBody()?.string()
                val res = Gson().fromJson(errorResponse, NewsResponse::class.java)
                _state.postValue(
                    NewsContract.State.Error(ErrorContainer(
                        res.message ?: "Something went wrong"
                    ) { getNewsSources(id, query) })
                )

            } catch (e: Exception) {
                _state.postValue(
                    NewsContract.State.Error(
                        ErrorContainer(
                            e.message ?: "Something went wrong"
                        ) {
                            getNewsSources(id, query)
                        })
                )
            }

        }
    }


}