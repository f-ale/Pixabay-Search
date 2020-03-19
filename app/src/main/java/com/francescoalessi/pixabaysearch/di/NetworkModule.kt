package com.francescoalessi.pixabaysearch.di

import com.francescoalessi.pixabaysearch.api.PixabayService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule
{
    private val baseUrl = "https://pixabay.com/"

    /*
     *   Provides Retrofit Pixabay Service for injection
     */
    @Singleton
    @Provides
    fun providePixabayService(): PixabayService
    {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(PixabayService::class.java)
    }
}