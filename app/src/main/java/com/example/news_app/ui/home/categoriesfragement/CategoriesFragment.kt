package com.example.news_app.ui.home.categoriesfragement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.news_app.R
import com.example.news_app.databinding.FragmentCategoriesBinding
import com.example.news_app.ui.home.HomeActivity
import com.example.news_app.ui.home.SharedViewModel
import com.example.news_app.ui.home.newsfragment.NewsFragment

class CategoriesFragment : Fragment() {
    lateinit var viewBinding: FragmentCategoriesBinding
    val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeActivity).viewBinding.search.isVisible = false
        startNews()
    }

    private fun startNews() {
        viewBinding.sports.setOnClickListener {
            startFragment()
            sharedViewModel.setterCategory(it.tag.toString())
        }
        viewBinding.politics.setOnClickListener {
            startFragment()
            sharedViewModel.setterCategory(it.tag.toString())
        }
        viewBinding.health.setOnClickListener {
            startFragment()
            sharedViewModel.setterCategory(it.tag.toString())
        }
        viewBinding.bussiness.setOnClickListener {
            startFragment()
            sharedViewModel.setterCategory(it.tag.toString())
        }
        viewBinding.enviroment.setOnClickListener {
            startFragment()
            sharedViewModel.setterCategory(it.tag.toString())
        }
        viewBinding.science.setOnClickListener {
            startFragment()
            sharedViewModel.setterCategory(it.tag.toString())
        }
    }

    private fun startFragment() {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.container, NewsFragment())
            .commit()
    }



}