package com.mockdroid.pekjetpek.ui.movie

import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity

interface MovieFragmentCallback {
    fun onDetailClick(movie: MovieEntity)
    fun onShareClick(movie: MovieEntity)
}