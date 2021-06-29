package com.mockdroid.pekjetpek.di

import android.content.Context
import com.mockdroid.pekjetpek.data.source.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): MovieTvShowRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieTvShowRepository.getInstance(remoteDataSource)
    }
}