package com.francescoalessi.pixabaysearch.di

import androidx.lifecycle.ViewModel
import com.alexfacciorusso.daggerviewmodel.ViewModelKey
import com.francescoalessi.pixabaysearch.ui.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchViewModelModule
{
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindsSearchViewModel(searchViewModel: SearchViewModel) : ViewModel
}