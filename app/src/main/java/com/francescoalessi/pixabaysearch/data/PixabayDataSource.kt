package com.francescoalessi.pixabaysearch.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.francescoalessi.pixabaysearch.BuildConfig
import com.francescoalessi.pixabaysearch.api.PixabayService
import com.francescoalessi.pixabaysearch.model.PixabayImage
import com.francescoalessi.pixabaysearch.model.Repository
import com.francescoalessi.pixabaysearch.model.SearchResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PixabayDataSource(private val requestInterface: PixabayService, private val searchQuery:String) : PageKeyedDataSource<Int, PixabayImage>()
{
    private val disposables = CompositeDisposable()
    private val apiKey: String = BuildConfig.API_KEY

    // LiveData to observe if network errors have occurred
    val isError = MutableLiveData<Boolean>()
    // LiveData to observe if no results have been found for the query
    val noResults = MutableLiveData<Boolean>()

    /*
     *  Loads first 'page' of data
     */
    override fun loadInitial
    (
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PixabayImage>
    )
    {
        disposables.add(requestInterface
            .getSearchResult(apiKey, searchQuery, 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    result: SearchResult ->
                    // Return the data to the callback
                    callback.onResult(result.hits, null, 2)

                    // Update other livedata according to the response
                    isError.value = false
                    noResults.value = result.hits.isEmpty()
                },
                {
                    // If an error has occurred, update the livedata
                    isError.value = true
                }
            )
        )

    }

    /*
     *  Loads subsequent pages of data
     */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PixabayImage>)
    {
        disposables.add(requestInterface.getSearchResult(apiKey, searchQuery, params.key)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    result: SearchResult -> callback.onResult(result.hits, params.key + 1)
                },
                {
                    // TODO: Let the UI know more content couldn't be loaded
                }
            )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PixabayImage>)
    {
        // not needed
    }
}