package com.francescoalessi.pixabaysearch.api

import com.francescoalessi.pixabaysearch.model.SearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService
{
    /*
     *  Defines the query to retrieve search results from the Pixabay API
     */
    @GET("/api/")
    fun getSearchResult(
        @Query("key") apiKey: String,
        @Query("q") search: String,
        @Query("page") page: Int
    ): Observable<SearchResult>
}