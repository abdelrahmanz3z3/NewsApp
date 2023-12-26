package com.example.news_app.common.bindingclasses

data class ErrorContainer(
    var message: String?,
    var onTryAgainClickListener: OnTryAgainClickListener?
)

fun interface OnTryAgainClickListener {
    fun onClick()
}

