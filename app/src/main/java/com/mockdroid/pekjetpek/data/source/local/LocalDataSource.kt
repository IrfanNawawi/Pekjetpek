package com.mockdroid.pekjetpek.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity
import com.mockdroid.pekjetpek.data.source.local.room.MovieTvShowDao
import com.mockdroid.pekjetpek.utils.Const.Companion.MOVIE_TMDB
import com.mockdroid.pekjetpek.utils.Const.Companion.TVSHOW_TMDB
import com.mockdroid.pekjetpek.utils.SortUtils

class LocalDataSource private constructor(private val mMovieTvShowDao: MovieTvShowDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieTvShowDao: MovieTvShowDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieTvShowDao)
    }

    fun getAllMovies(sort: String): DataSource.Factory<Int, MovieEntity> =
        mMovieTvShowDao.getMovies(
            SortUtils.getSortedQuery(sort, MOVIE_TMDB)
        )

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> =
        mMovieTvShowDao.getDetailMovieById(movieId)

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> =
        mMovieTvShowDao.getFavoriteMovies()

    fun insertMovies(movies: List<MovieEntity>) = mMovieTvShowDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mMovieTvShowDao.updateMovies(movie)
    }

    fun updateMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mMovieTvShowDao.updateMovies(movie)
    }


    fun getAllTvShows(sort: String): DataSource.Factory<Int, TvShowEntity> =
        mMovieTvShowDao.getTvShows(SortUtils.getSortedQuery(sort, TVSHOW_TMDB))

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> =
        mMovieTvShowDao.getDetailTvShowById(tvShowId)

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> =
        mMovieTvShowDao.getFavoriteTvShows()

    fun insertTvShows(tvShows: List<TvShowEntity>) = mMovieTvShowDao.insertTvShows(tvShows)

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mMovieTvShowDao.updateTvShows(tvShow)
    }

    fun updateTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        mMovieTvShowDao.updateTvShows(tvShow)
    }
}