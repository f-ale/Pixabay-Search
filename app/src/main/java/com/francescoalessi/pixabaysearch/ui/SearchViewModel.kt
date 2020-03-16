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

class SearchViewModel() : ViewModel() {
    // TODO: Implement the ViewModel

    val disposables : CompositeDisposable = CompositeDisposable()
    var mSearchResults : MutableLiveData<List<PixabayImage>> = MutableLiveData()

    init {
        newSearch("fruits")
    }

    fun newSearch(query:String)
    {
        disposables.add(Repository.getSearchResults(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result: SearchResult ->  mSearchResults.value = result.hits}))
    }

    fun getSearchResults()  : LiveData<List<PixabayImage>>
    {
        return mSearchResults
    }

    @Override
    override fun onCleared() {
        disposables.clear()
    }
}
