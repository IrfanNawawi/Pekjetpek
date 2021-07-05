package com.mockdroid.pekjetpek.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mockdroid.pekjetpek.data.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity
import com.mockdroid.pekjetpek.utils.Const.Companion.TYPE_MOVIE
import com.mockdroid.pekjetpek.utils.Const.Companion.TYPE_TVSHOW
import com.mockdroid.pekjetpek.vo.Resource

class DetailMovieViewModel(private val movieTvShowRepository: MovieTvShowRepository) : ViewModel() {
    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>
    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>

    fun getDetailMovie() = detailMovie
    fun getDetailTvShow() = detailTvShow

    fun setSelectedTMDB(dataId: String, category: String) {
        when (category) {
            TYPE_MOVIE -> {
                detailMovie = movieTvShowRepository.getMovieDetail(dataId.toInt())
            }

            TYPE_TVSHOW -> {
                detailTvShow = movieTvShowRepository.getTvShowDetail(dataId.toInt())
            }
        }
    }

    fun setFavoriteMovie() {
        val resource = detailMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.isFavorite
            movieTvShowRepository.setFavoriteMovie(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTvShow.value
        if (resource?.data != null) {
            val newState = !resource.data.isFavorite
            movieTvShowRepository.setFavoriteTvShow(resource.data, newState)
        }
    }
}