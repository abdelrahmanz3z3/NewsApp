package com.example.news_app.ui.home.newsfragment

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.News
import com.example.domain.model.Sources
import com.example.news_app.common.SingleLiveEvent
import com.example.news_app.common.bindingclasses.ErrorContainer

class NewsContract {

    interface ViewModel {
        fun invokeAction(action: Action)
        val state: MutableLiveData<State>
        val event: SingleLiveEvent<Event>
    }

    sealed class Action {
        class LoadSources(val category: String) : Action()
        class LoadNews(val source: String, val q: String?) : Action()
    }

    sealed class State {
        class SourcesSuccess(val sources: List<Sources?>?) : State()
        class NewsSuccess(val sources: List<News?>?) : State()
        class Error(val error: ErrorContainer) : State()
        class Loading(val message: String) : State()
    }

    sealed class Event {
        class NavigateBetweenSources(val source: String) : Event()
    }
}