package com.example.news_app.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var category = MutableLiveData<String>()
    fun setterCategory(category: String) {
        this.category.value = category
    }

}