package com.francescoalessi.pixabaysearch

import androidx.multidex.MultiDexApplication
import com.francescoalessi.pixabaysearch.di.AppComponent
import com.francescoalessi.pixabaysearch.di.DaggerAppComponent

class PixabayApplication : MultiDexApplication()
{
    // Reference to the DI application graph that is used across the whole app
    val appComponent: AppComponent = DaggerAppComponent.create()
}