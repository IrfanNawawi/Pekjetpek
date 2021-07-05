package com.mockdroid.pekjetpek.di

import android.content.Context
import com.mockdroid.pekjetpek.data.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.local.LocalDataSource
import com.mockdroid.pekjetpek.data.source.local.room.MovieTvShowDatabase
import com.mockdroid.pekjetpek.data.source.remote.RemoteDataSource
import com.mockdroid.pekjetpek.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieTvShowRepository {
        val database = MovieTvShowDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieTvShow())
        val appExecutors = AppExecutors()

        return MovieTvShowRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}