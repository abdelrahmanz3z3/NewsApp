package com.example.news_app.ui.bindingclasses

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("url")
fun bindUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView)
        .load(url)
        .into(imageView)
}

@BindingAdapter("linkUrl")
fun launchUrl(view: View, url: String?) {
    view.setOnClickListener {
        it.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}