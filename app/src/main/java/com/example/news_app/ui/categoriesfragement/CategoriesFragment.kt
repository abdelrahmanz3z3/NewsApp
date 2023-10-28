package com.example.news_app.ui.categoriesfragement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.news_app.R
import com.example.news_app.databinding.FragmentCategoriesBinding
import com.example.news_app.ui.newsfragment.NewsFragment

class CategoriesFragment : Fragment() {
    lateinit var viewBinding: FragmentCategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startNews()
    }

    private fun startNews() {
        viewBinding.sports.setOnClickListener {
            startFragment(it.tag.toString())
        }
        viewBinding.politics.setOnClickListener {
            startFragment(it.tag.toString())
        }
        viewBinding.health.setOnClickListener {
            startFragment(it.tag.toString())
        }
        viewBinding.bussiness.setOnClickListener {
            startFragment(it.tag.toString())
        }
        viewBinding.enviroment.setOnClickListener {
            startFragment(it.tag.toString())
        }
        viewBinding.science.setOnClickListener {
            startFragment(it.tag.toString())
        }
    }

    fun startFragment(tag: String) {
        val fr = NewsFragment(true)
        var b = Bundle()
        b.putString("cat", tag)
        fr.arguments = b
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.container, fr)
            .commit()
        onClickedItem?.onClick(tag)
    }

    var onClickedItem: OnClickedItem? = null

    fun interface OnClickedItem {
        fun onClick(s: String)
    }


}