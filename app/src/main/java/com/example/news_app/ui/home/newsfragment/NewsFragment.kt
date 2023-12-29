package com.example.news_app.ui.home.newsfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.domain.model.News
import com.example.domain.model.Sources
import com.example.news_app.common.bindingclasses.ErrorContainer
import com.example.news_app.common.dialogextension.showMessage
import com.example.news_app.databinding.FragmentNewsBinding
import com.example.news_app.ui.details.DetailsActivity
import com.example.news_app.ui.home.HomeActivity
import com.example.news_app.ui.home.SharedViewModel
import com.facebook.shimmer.Shimmer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var viewBinding: FragmentNewsBinding
    private val adapter = NewsAdapter(null)
    private lateinit var vm: NewsViewModel
    val sharedViewModel: SharedViewModel by activityViewModels()
    private var query: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return viewBinding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObserves()
    }


    private fun initViews() {
        viewBinding.vm = vm
        viewBinding.lifecycleOwner = this
        viewBinding.rec.adapter = adapter
        (activity as HomeActivity).viewBinding.search.isVisible = true

        adapter.onItemClickListener = NewsAdapter.OnItemClickListener { position, item ->
            vm.invokeAction(NewsContract.Action.GoToDetailsActivity(item))
        }
        (activity as HomeActivity).viewBinding.search.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                query = newText
                vm.invokeAction(NewsContract.Action.LoadNews(s?.id!!, query))
                return true
            }

        })


    }

    private fun startActivity(item: News) {
        val i = Intent(requireContext(), DetailsActivity::class.java)
        i.putExtra("item", item)
        startActivity(i)

    }

    private fun handleStates(state: StateFlow<NewsContract.State>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                state.collect { state ->
                    when (state) {
                        is NewsContract.State.Error -> {
                            handelError(state.error)
                        }

                        is NewsContract.State.LoadingNews -> {
                            showShimmer()
                        }

                        is NewsContract.State.LoadingSources -> {
                            hideShimmer()
                            viewBinding.tabLoading.isVisible = true
                        }

                        is NewsContract.State.NewsSuccess -> {
                            adapter.bindNews(state.sources)
                            hideShimmer()
                        }

                        is NewsContract.State.SourcesSuccess -> {
                            bindTab(state.sources)
                            viewBinding.tabLoading.isVisible = false
                        }
                    }

                }
            }
        }
    }

    private fun initObserves() {
        handleStates(vm.state)
        vm.event.observe(viewLifecycleOwner, ::handleEvents)
        sharedViewModel.category.observe(viewLifecycleOwner) {
            when (it) {
                "general" -> (activity as HomeActivity).viewBinding.toolbar.title = "السياسه"
                "sports" -> (activity as HomeActivity).viewBinding.toolbar.title = "الرياضه"
                "health" -> (activity as HomeActivity).viewBinding.toolbar.title = "الصحة"
                "business" -> (activity as HomeActivity).viewBinding.toolbar.title = "الاقتصادية"
                "technology" -> (activity as HomeActivity).viewBinding.toolbar.title = "البيئة"
                "science" -> (activity as HomeActivity).viewBinding.toolbar.title = "العلمية"

            }
            vm.invokeAction(NewsContract.Action.LoadSources(it))
        }
    }

    private fun handleEvents(event: NewsContract.Event) {
        when (event) {
            is NewsContract.Event.NavigateToDetails -> {
                startActivity(event.item)
            }
        }
    }

    private fun showShimmer() {
        val shimmer = Shimmer.AlphaHighlightBuilder().setAutoStart(true).setBaseAlpha(0.25f)
            .setHighlightAlpha(0.75f).setDirection(Shimmer.Direction.LEFT_TO_RIGHT).build()
        viewBinding.rec.isVisible = false
        viewBinding.shimmer.setShimmer(shimmer)
        viewBinding.shimmer.startShimmer()
        viewBinding.shimmer.isVisible = true
    }

    private fun hideShimmer() {
        viewBinding.rec.isVisible = true
        viewBinding.shimmer.stopShimmer()
        viewBinding.shimmer.isVisible = false

    }


    var s: Sources? = null
    private fun bindTab(sources: List<Sources?>?) {
        if (sources == null)
            return
        sources.forEach {
            val tab = viewBinding.tablayout.newTab()
            tab.text = it?.name
            tab.tag = it
            viewBinding.tablayout.addTab(tab)
        }
        viewBinding.tablayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                s = tab?.tag as Sources
                vm.invokeAction(NewsContract.Action.LoadNews(s?.id!!, query))

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                s = tab?.tag as Sources
                vm.invokeAction(NewsContract.Action.LoadNews(s?.id!!, query))
            }
        })
        viewBinding.tablayout.getTabAt(0)?.select()

    }


    private fun handelError(errorContainer: ErrorContainer) {
        showMessage(
            errorContainer.message ?: "Something went wrong",
            posMessage = "Try again",
            posAction = { dialog, _ ->
                errorContainer.onTryAgainClickListener?.onClick()
                dialog.dismiss()
            },
            negMessage = "cancel",
            negAction = { dialog, _ ->
                dialog.dismiss()
                exitProcess(0)
            })
    }


}