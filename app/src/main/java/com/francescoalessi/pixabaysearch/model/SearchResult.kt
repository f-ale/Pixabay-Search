package com.francescoalessi.pixabaysearch.model

import com.google.gson.annotations.SerializedName

/*
    Data object for parsing Pixabay search results
 */

data class SearchResult
(
    @SerializedName("hits")
    val hits: List<PixabayImage>
)
