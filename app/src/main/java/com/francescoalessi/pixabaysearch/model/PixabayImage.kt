package com.francescoalessi.pixabaysearch.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.francescoalessi.pixabaysearch.R
import com.google.gson.annotations.SerializedName

data class PixabayImage
(
    @SerializedName("id")
    val id: Int,

    @SerializedName("webformatURL")
    val imageURL: String,

    @SerializedName("largeImageURL")
    val largeImageURL: String,

    @SerializedName("user")
    val username: String,

    @SerializedName("tags")
    val tags: String,

    @SerializedName("likes")
    val likes: Int,

    @SerializedName("comments")
    val comments: Int,

    @SerializedName("favorites")
    val favourites: Int
)

@BindingAdapter("image")
fun loadImage(imageView: ImageView, imageURL: String)
{
    Glide.with(imageView.context)
        .load(imageURL)
        .placeholder(R.drawable.ic_image_black_24dp)
        .centerCrop()
        .into(imageView)
}