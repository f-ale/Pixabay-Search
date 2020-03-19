package com.francescoalessi.pixabaysearch.di

import com.alexfacciorusso.daggerviewmodel.DaggerViewModelInjectionModule
import com.francescoalessi.pixabaysearch.MainActivity
import com.francescoalessi.pixabaysearch.ui.ImageDetailFragment
import com.francescoalessi.pixabaysearch.ui.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =
[
    NetworkModule::class,
    DaggerViewModelInjectionModule::class,
    SearchViewModelModule::class
])

/*
 *  Dagger Dependency Injection main module
 */

interface AppComponent
{
    // Injection entry points
    fun inject(mainActivity: MainActivity)
    fun inject(searchFragment: SearchFragment)
    fun inject(imageDetailFragment: ImageDetailFragment)
}