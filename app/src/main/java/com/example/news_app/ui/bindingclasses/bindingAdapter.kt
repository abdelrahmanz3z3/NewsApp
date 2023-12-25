package com.example.news_app.ui.bindingclasses

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

@BindingAdapter("url")
fun bindUrl(imageView: ImageView, url: String?) {
    if (url.isNullOrBlank()) return

    val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(1000) // time to do one full sweep
        .setBaseAlpha(0.9f) // the alpha of the underlying children
        .setHighlightAlpha(0.6f) // the shimmer alpha amount
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()
    val shimmerDrawable = ShimmerDrawable()
    shimmerDrawable.setShimmer(shimmer)
    shimmerDrawable.startShimmer()

    Glide.with(imageView)
        .load(url)
        .placeholder(shimmerDrawable)
        .into(imageView)
}

@BindingAdapter("linkUrl")
fun launchUrl(view: View, url: String?) {
    view.setOnClickListener {
        it.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}