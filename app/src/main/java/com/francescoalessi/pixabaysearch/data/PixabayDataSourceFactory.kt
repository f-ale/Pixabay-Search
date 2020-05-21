package com.francescoalessi.pixabaysearch.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.francescoalessi.pixabaysearch.api.PixabayService
import com.francescoalessi.pixabaysearch.model.PixabayImage
import javax.inject.Inject

class PixabayDataSourceFactory @Inject constructor(private val requestInterface: PixabayService): DataSource.Factory<Int, PixabayImage>()
{
    var searchQuery : String = "fruits"

    // LiveData used to gain information about latest network state updates
    val latestDataSource = MutableLiveData<PixabayDataSource>()

    /*
     *  Creates a new instance of the data source
     */
    override fun create(): DataSource<Int, PixabayImage>
    {
        val dataSource = PixabayDataSource(requestInterface, searchQuery)
        latestDataSource.postValue(dataSource)
        return dataSource
    }
}