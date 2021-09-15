package com.example.ecommercie_1

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        //Note glide downloads images in background thread
        GlideApp.with(view.context).load(Firebase.storage.getReferenceFromUrl(url)).into(view)
    }

}