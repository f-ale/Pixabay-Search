package com.francescoalessi.pixabaysearch.di

import com.francescoalessi.pixabaysearch.MainActivity
import com.francescoalessi.pixabaysearch.ui.SearchViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent
{
    fun inject(viewModel: SearchViewModel)
    fun inject(mainActivity: MainActivity)
}