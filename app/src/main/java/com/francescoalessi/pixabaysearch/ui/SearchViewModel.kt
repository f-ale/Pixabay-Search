package com.francescoalessi.pixabaysearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.francescoalessi.pixabaysearch.data.PixabayDataSourceFactory
import com.francescoalessi.pixabaysearch.model.PixabayImage
import com.francescoalessi.pixabaysearch.model.Repository
import com.francescoalessi.pixabaysearch.model.SearchResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel()
{
    private val disposables: CompositeDisposable = CompositeDisposable()
    private val searchResult : LiveData<PagedList<PixabayImage>> = repository.getSearchResults()
    private val connectionError = repository.getErrorState()
    private val noResults = repository.getNoResults()

    fun newSearch(searchQuery:String)
    {
        repository.setSearchQuery(searchQuery)
        searchResult.value?.dataSource?.invalidate()
    }

    fun getSearchResults(): LiveData<PagedList<PixabayImage>>
    {
        return searchResult
    }

    fun getConnectionError(): LiveData<Boolean>
    {
        return connectionError
    }

    fun getNoResultsFound() : LiveData<Boolean>
    {
        return noResults
    }

    @Override
    override fun onCleared()
    {
        disposables.clear()
    }
}
