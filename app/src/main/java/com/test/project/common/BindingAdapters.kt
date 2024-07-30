package com.test.project.common

import android.widget.TextView
import androidx.databinding.BindingAdapter


object BindingAdapters {
    @JvmStatic
    @BindingAdapter("formattedPrice")
    fun TextView.setFormattedPrice(price: Long?) {
        val formattedPrice = price?.let { "$it" } ?: ""
        text = formattedPrice
    }
}
