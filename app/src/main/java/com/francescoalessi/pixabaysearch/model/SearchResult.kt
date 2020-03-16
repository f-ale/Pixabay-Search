package com.francescoalessi.pixabaysearch.model

import com.google.gson.annotations.SerializedName

data class SearchResult
(
    @SerializedName("hits")
    val hits: List<PixabayImage>
)
