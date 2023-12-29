package com.example.news_app.ui.home.newsfragment

import com.example.domain.model.News
import com.example.domain.model.Sources
import com.example.news_app.common.SingleLiveEvent
import com.example.news_app.common.bindingclasses.ErrorContainer
import kotlinx.coroutines.flow.StateFlow

class NewsContract {
    interface ViewModel {
        fun invokeAction(action: Action)
        val state: StateFlow<State>
        val event: SingleLiveEvent<Event>
    }

    sealed class Action {
        class LoadSources(val category: String) : Action()
        class LoadNews(val source: String, val q: String?) : Action()

        class GoToDetailsActivity(val item: News) : Action()
    }

    sealed class State {
        class SourcesSuccess(val sources: List<Sources?>?) : State()
        class NewsSuccess(val sources: List<News?>?) : State()
        class Error(val error: ErrorContainer) : State()
        data object LoadingSources : State()
        data object LoadingNews : State()
    }

    sealed class Event {
        class NavigateToDetails(val item: News) : Event()
    }
}
