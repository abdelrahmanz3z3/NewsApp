package com.example.news_app.common.bindingclasses

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
    if (url.isNullOrBlank()) {
        return
    }
    val shimmer = Shimmer.AlphaHighlightBuilder().setAutoStart(true).setBaseAlpha(0.7f)
        .setHighlightAlpha(0.6f).setDirection(Shimmer.Direction.LEFT_TO_RIGHT).build()
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