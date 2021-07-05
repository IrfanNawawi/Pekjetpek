package com.mockdroid.pekjetpek.ui.favorite.movie_fav

import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity

interface MovieFavoriteFragmentCallback {
    fun onDetailClick(movie: MovieEntity)
    fun onShareClick(movie: MovieEntity)
}