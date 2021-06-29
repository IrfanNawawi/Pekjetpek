package com.mockdroid.pekjetpek.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mockdroid.pekjetpek.data.source.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.remote.response.MovieItem

class MovieViewModel(private val movieTvShowRepository: MovieTvShowRepository) : ViewModel() {
    fun getMovie(): LiveData<List<MovieItem>> = movieTvShowRepository.getAllMovies()
}