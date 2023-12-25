package com.example.news_app.ui.newsfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.data.api.model.newsresponse.ArticlesItem
import com.example.data.api.model.sourcesresponse.SourcesItem
import com.example.news_app.databinding.FragmentNewsBinding
import com.example.news_app.dialogextension.showMessage
import com.example.news_app.ui.bindingclasses.ErrorContainer
import com.example.news_app.ui.detailsactivity.DetailsActivity
import com.example.news_app.ui.home.HomeActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlin.system.exitProcess

class NewsFragment(enabled: Boolean) : Fragment() {
    private lateinit var viewBinding: FragmentNewsBinding
    private val adapter = NewsAdapter(null)
    lateinit var vm: NewsViewModel
    lateinit var category: String
    var query: String? = null
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
        receiveCat()
        vm.getSources(category)
    }


    private fun initViews() {
        viewBinding.vm = vm
        viewBinding.lifecycleOwner = this
        viewBinding.rec.adapter = adapter
        adapter.onItemClickListener = NewsAdapter.OnItemClickListener { position, item ->
            startActivity(item)
        }
        (activity as HomeActivity).viewBinding.search.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                query = newText
                vm.getNewsSources(s?.id, query = query)
                return true
            }

        })


    }

    private fun startActivity(item: ArticlesItem) {
        val i = Intent(requireContext(), DetailsActivity::class.java)
        i.putExtra("item", item)
        startActivity(i)


    }

    fun receiveCat() {
        val args = arguments
        category = args?.getString("cat").toString()
    }


    fun initObserves() {
        vm.sourcesData.observe(viewLifecycleOwner) {
            bindTab(it)
        }
        vm.error.observe(viewLifecycleOwner) {
            handelError(it)
        }
        vm.articlesData.observe(viewLifecycleOwner) {
            adapter.bindNews(it)
        }
    }


    var s: SourcesItem? = null
    fun bindTab(sources: List<SourcesItem?>?) {
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
                s = tab?.tag as SourcesItem
                vm.getNewsSources(s?.id, query = query)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                s = tab?.tag as SourcesItem
                vm.getNewsSources(s?.id, query = query)
            }
        })
        viewBinding.tablayout.getTabAt(0)?.select()

    }


    fun handelError(errorContainer: ErrorContainer) {
        showMessage(errorContainer.message ?: "Something went wrong",
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