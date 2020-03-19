package com.francescoalessi.pixabaysearch.model

import com.francescoalessi.pixabaysearch.BuildConfig
import com.francescoalessi.pixabaysearch.api.PixabayService
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor(private val requestInterface: PixabayService)
{
    private var apiKey: String = BuildConfig.API_KEY

    fun getSearchResults(searchQuery: String): Observable<SearchResult>
    {
        return requestInterface
            .getSearchResult(apiKey, searchQuery)
    }
}