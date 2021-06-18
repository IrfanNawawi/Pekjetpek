package com.mockdroid.pekjetpek.ui.detail

import androidx.lifecycle.ViewModel
import com.mockdroid.pekjetpek.data.MovieEntity
import com.mockdroid.pekjetpek.utils.DataDummy

class DetailMovieViewModel : ViewModel() {
    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateDummyMovies()
        for (movieEntity in movieEntities) {
            if (movieEntity.id == movieId) {
                movie = movieEntity
            }
        }

        return movie
    }

    fun getTvShow(): MovieEntity {
        lateinit var tvShow: MovieEntity
        val tvShowEntities = DataDummy.generateDummyTvShow()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.id == movieId) {
                tvShow = tvShowEntity
            }
        }

        return tvShow
    }
}