package com.mockdroid.pekjetpek.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mockdroid.pekjetpek.data.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.vo.Resource

class MovieViewModel(private val movieTvShowRepository: MovieTvShowRepository) : ViewModel() {
    fun getMovie(sort: String): LiveData<Resource<PagedList<MovieEntity>>> =
        movieTvShowRepository.getAllMovies(sort)
}