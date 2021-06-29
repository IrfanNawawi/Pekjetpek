package com.mockdroid.pekjetpek.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mockdroid.pekjetpek.data.source.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.remote.response.MovieItem
import com.mockdroid.pekjetpek.data.source.remote.response.TvShowItem

class DetailMovieViewModel(private val movieTvShowRepository: MovieTvShowRepository) : ViewModel() {
    fun getMovieDetail(movieId: Int): LiveData<MovieItem> =
        movieTvShowRepository.getMovieDetail(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowItem> =
        movieTvShowRepository.getTvShowDetail(tvShowId)
}