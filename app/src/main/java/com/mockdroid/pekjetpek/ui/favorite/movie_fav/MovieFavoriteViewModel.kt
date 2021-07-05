package com.mockdroid.pekjetpek.ui.favorite.movie_fav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mockdroid.pekjetpek.data.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity

class MovieFavoriteViewModel(private val movieTvShowRepository: MovieTvShowRepository) :
    ViewModel() {
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> =
        movieTvShowRepository.getFavoriteMovies()

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFavorite
        movieTvShowRepository.setFavoriteMovie(movieEntity, newState)
    }
}