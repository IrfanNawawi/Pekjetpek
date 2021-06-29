package com.mockdroid.pekjetpek.data.source

import androidx.lifecycle.LiveData
import com.mockdroid.pekjetpek.data.source.remote.response.MovieItem
import com.mockdroid.pekjetpek.data.source.remote.response.TvShowItem

interface MovieTvShowDataSource {
    fun getAllMovies(): LiveData<List<MovieItem>>
    fun getAllTvShows(): LiveData<List<TvShowItem>>
    fun getMovieDetail(movieId: Int): LiveData<MovieItem>
    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowItem>
}