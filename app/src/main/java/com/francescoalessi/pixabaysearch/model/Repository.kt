package com.francescoalessi.pixabaysearch.model

import android.app.Application
import com.francescoalessi.pixabaysearch.PixabayApplication
import com.francescoalessi.pixabaysearch.R
import com.francescoalessi.pixabaysearch.api.PixabayService
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Repository
{
    val BASEURL = "https://pixabay.com/"

    //TODO: get api key from string resources
    lateinit var apiKey : String

    val requestInterface = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(PixabayService::class.java)

    fun getSearchResults(searchQuery: String): Observable<SearchResult>
    {
        return requestInterface
            .getSearchResult(apiKey, searchQuery)
    }
}