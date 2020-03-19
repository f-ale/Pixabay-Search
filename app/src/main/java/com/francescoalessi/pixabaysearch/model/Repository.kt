package com.francescoalessi.pixabaysearch.model

import android.app.Application
import com.francescoalessi.pixabaysearch.BuildConfig
import com.francescoalessi.pixabaysearch.PixabayApplication
import com.francescoalessi.pixabaysearch.R
import com.francescoalessi.pixabaysearch.api.PixabayService
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class Repository @Inject constructor(val requestInterface : PixabayService)
{
    //TODO: REMOVE, get api key from string resources
    var apiKey : String = BuildConfig.API_KEY

    fun getSearchResults(searchQuery: String): Observable<SearchResult>
    {
        return requestInterface
            .getSearchResult(apiKey, searchQuery)
    }
}