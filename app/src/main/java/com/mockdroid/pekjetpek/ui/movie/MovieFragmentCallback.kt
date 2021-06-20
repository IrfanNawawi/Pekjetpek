package com.mockdroid.pekjetpek.ui.movie

import com.mockdroid.pekjetpek.data.MovieEntity

interface MovieFragmentCallback {
    fun onShareClick(movie: MovieEntity)
}