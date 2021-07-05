package com.mockdroid.pekjetpek.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity
import com.mockdroid.pekjetpek.vo.Resource

interface MovieTvShowDataSource {
    fun getAllMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>>
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun getAllTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>>
    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>
    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}