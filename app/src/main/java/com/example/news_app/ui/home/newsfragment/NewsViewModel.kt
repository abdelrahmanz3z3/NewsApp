package com.example.news_app.ui.home.newsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultWrapper
import com.example.domain.usecases.NewsUseCase
import com.example.domain.usecases.SourcesUseCase
import com.example.news_app.common.SingleLiveEvent
import com.example.news_app.common.bindingclasses.ErrorContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val sources: SourcesUseCase,
    private val news: NewsUseCase
) : ViewModel(), NewsContract.ViewModel {

    private val _event = SingleLiveEvent<NewsContract.Event>()
    private val _state = MutableStateFlow<NewsContract.State>(NewsContract.State.LoadingSources)
    override val state: StateFlow<NewsContract.State>
        get() = _state

    override val event: SingleLiveEvent<NewsContract.Event>
        get() = _event


    override fun invokeAction(action: NewsContract.Action) {

        when (action) {
            is NewsContract.Action.LoadNews -> {
                getNewsSources(action.source, action.q)
            }

            is NewsContract.Action.LoadSources -> {
                getSources(action.category)
            }

            is NewsContract.Action.GoToDetailsActivity -> {
                _event.postValue(NewsContract.Event.NavigateToDetails(action.item))
            }
        }
    }

    private fun getSources(cat: String) {

        viewModelScope.launch {
            val response = sources.getSources(cat)
            response.collect {
                when (it) {
                    is ResultWrapper.Error -> {
                        _state.emit(
                            NewsContract.State.Error(ErrorContainer(
                                it.error.localizedMessage ?: "Something went wrong"
                            ) {
                                getSources(cat)
                            })
                        )
                    }

                    ResultWrapper.Loading -> {
                        _state.emit(NewsContract.State.LoadingSources)
                    }

                    is ResultWrapper.ServerException -> {
                        _state.emit(
                            NewsContract.State.Error(ErrorContainer(
                                it.serverError.localizedMessage ?: "Something went wrong"
                            ) {
                                getSources(cat)
                            })
                        )
                    }

                    is ResultWrapper.Success -> {
                        _state.emit(NewsContract.State.SourcesSuccess(it.data))
                    }
                }

            }
        }
    }

    private fun getNewsSources(id: String?, query: String? = null) {
        viewModelScope.launch {

            val response = news.getNews(id ?: "", q = query)
            response.collect {
                when (it) {
                    is ResultWrapper.Error -> {
                        _state.emit(
                            NewsContract.State.Error(ErrorContainer(
                                it.error.localizedMessage ?: "Something went wrong"
                            ) { getNewsSources(id, query) })
                        )
                    }

                    ResultWrapper.Loading -> {
                        _state.emit(NewsContract.State.LoadingNews)
                    }

                    is ResultWrapper.ServerException -> {
                        _state.emit(
                            NewsContract.State.Error(ErrorContainer(
                                it.serverError.localizedMessage ?: "Something went wrong"
                            ) { getNewsSources(id, query) })
                        )
                    }

                    is ResultWrapper.Success -> {
                        _state.emit(NewsContract.State.NewsSuccess(it.data))
                    }
                }
            }
        }

    }
}