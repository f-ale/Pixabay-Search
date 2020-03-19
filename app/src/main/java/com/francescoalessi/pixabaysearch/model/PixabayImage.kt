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

    @SerializedName("previewURL")
    val previewURL: String,

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

/*
    Data Binding method for loading list item's thumbnails
 */

@BindingAdapter("image", "thumbnail")
fun loadImage(imageView: ImageView, imageURL: String, previewURL:String)
{
    Glide.with(imageView.context)
        .load(imageURL)
        .thumbnail(Glide.with(imageView.context)
            .load(previewURL)
            .centerCrop())
        .centerCrop()
        .into(imageView)
}

/*
    Data Binding method for loading images in detail view
 */

@BindingAdapter("bigImage", "thumbnail")
fun loadBigImage(imageView: ImageView, imageURL: String, previewURL:String)
{
    Glide.with(imageView.context)
        .load(imageURL)
        .thumbnail(Glide.with(imageView.context)
            .load(previewURL)
            .fitCenter())
        .fitCenter()
        .into(imageView)
}