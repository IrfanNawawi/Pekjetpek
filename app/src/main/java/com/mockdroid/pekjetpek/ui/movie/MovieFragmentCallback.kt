package com.mockdroid.pekjetpek.ui.movie

import com.mockdroid.pekjetpek.data.source.remote.response.MovieItem

interface MovieFragmentCallback {
    fun onDetailClick(movie: MovieItem)
    fun onShareClick(movie: MovieItem)
}