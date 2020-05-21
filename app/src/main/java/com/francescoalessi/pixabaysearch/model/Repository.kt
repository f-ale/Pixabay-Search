package com.francescoalessi.pixabaysearch.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.francescoalessi.pixabaysearch.data.PixabayDataSourceFactory
import javax.inject.Inject

class Repository @Inject constructor(private val dataSourceFactory: PixabayDataSourceFactory)
{
    /*
     *  Sets a new search query for future data sources
     */
    fun setSearchQuery(searchQuery:String)
    {
        dataSourceFactory.searchQuery = searchQuery
    }

    fun getSearchResults(): LiveData<PagedList<PixabayImage>>
    {
        return dataSourceFactory.toLiveData(20)
    }

    fun getErrorState() : LiveData<Boolean>
    {
        return dataSourceFactory.latestDataSource.switchMap{it.isError}
    }

    fun getNoResults() : LiveData<Boolean>
    {
        return dataSourceFactory.latestDataSource.switchMap{it.noResults}
    }
}