package com.francescoalessi.pixabaysearch

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.francescoalessi.pixabaysearch.di.DaggerAppComponent

// appComponent lives in the Application class to share its lifecycle
class PixabayApplication : Application()
{
    // Reference to the application graph that is used across the whole app
    val appComponent = DaggerAppComponent.create()
}