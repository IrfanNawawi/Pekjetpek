package com.mockdroid.pekjetpek.ui.favorite.tvshow_fav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mockdroid.pekjetpek.data.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity

class TvShowFavoriteViewModel(private val movieTvShowRepository: MovieTvShowRepository) :
    ViewModel() {
    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> =
        movieTvShowRepository.getFavoriteTvShows()

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity) {
        val newState = !tvShowEntity.isFavorite
        movieTvShowRepository.setFavoriteTvShow(tvShowEntity, newState)
    }
}