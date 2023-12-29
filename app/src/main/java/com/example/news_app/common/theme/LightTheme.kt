package com.example.news_app.common.theme

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.news_app.R

class LightTheme : MyAppTheme {
    override fun firstActivityBackgroundColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.white)
    }

    override fun firstActivityTextColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.black)
    }

    override fun firstActivityIconColor(context: Context): Int {
        return ContextCompat.getColor(context, R.color.primaryColour)
    }

    override fun id(): Int {
        return 0
    }
}