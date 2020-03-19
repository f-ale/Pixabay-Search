package com.francescoalessi.pixabaysearch

import android.app.Application
import com.francescoalessi.pixabaysearch.di.DaggerAppComponent

class PixabayApplication : Application()
{
    // Reference to the DI application graph that is used across the whole app
    val appComponent = DaggerAppComponent.create()
}