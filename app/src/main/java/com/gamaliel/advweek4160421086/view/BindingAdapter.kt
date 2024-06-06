package com.gamaliel.advweek4160421086.view

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.gamaliel.advweek4160421086.R
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageUrl")
fun loadPhotoURL(imageView: ImageView, url: String?) {
    url?.let {
        Log.d("Picasso", "Loading image from URL: $url")

        val picasso = Picasso.Builder(imageView.context)
        picasso.listener { picasso, uri, exception ->
            Log.e("Picasso", "Error loading image from $uri", exception)
        }
        picasso.build()
            .load(url)
            .into(imageView)
    } ?: run {
        Log.e("Picasso", "URL is null")
    }
}
