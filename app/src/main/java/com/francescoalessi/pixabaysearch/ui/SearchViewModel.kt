package com.francescoalessi.pixabaysearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    private var mSearchResults: MutableLiveData<List<PixabayImage>> = MutableLiveData()
    private val connectionError: MutableLiveData<Boolean> = MutableLiveData(false)

    init
    {
        newSearch("fruits")
    }

    fun newSearch(query: String)
    {
        disposables.add(repository.getSearchResults(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result: SearchResult ->
                    mSearchResults.value = result.hits
                    connectionError.value = false
                },
                {
                    connectionError.value = true
                }
            )
        )
    }

    fun getSearchResults(): LiveData<List<PixabayImage>>
    {
        return mSearchResults
    }

    fun getConnectionError(): LiveData<Boolean>
    {
        return connectionError
    }

    @Override
    override fun onCleared()
    {
        disposables.clear()
    }
}
