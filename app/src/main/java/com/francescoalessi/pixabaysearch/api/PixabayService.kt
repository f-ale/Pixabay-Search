package com.francescoalessi.pixabaysearch.api

import com.francescoalessi.pixabaysearch.model.SearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService
{
    @GET("/api/")
    fun getSearchResult(
        @Query("key") apiKey: String,
        @Query("q") search: String
    ): Observable<SearchResult>
}