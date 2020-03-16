package com.francescoalessi.pixabaysearch

import android.app.Application
import com.francescoalessi.pixabaysearch.di.DaggerAppComponent
import com.francescoalessi.pixabaysearch.model.Repository

// appComponent lives in the Application class to share its lifecycle
class PixabayApplication : Application()
{
    override fun onCreate()
    {
        super.onCreate()
        Repository.apiKey = resources.getString(R.string.api_key)
    }
    // Reference to the application graph that is used across the whole app
    val appComponent = DaggerAppComponent.create()
}