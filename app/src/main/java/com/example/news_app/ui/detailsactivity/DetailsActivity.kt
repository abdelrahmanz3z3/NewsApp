package com.example.news_app.ui.detailsactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.data.api.model.newsresponse.ArticlesItem
import com.example.news_app.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val i = intent
        viewBinding.item = i.getParcelableExtra("item") as ArticlesItem?
    }


}