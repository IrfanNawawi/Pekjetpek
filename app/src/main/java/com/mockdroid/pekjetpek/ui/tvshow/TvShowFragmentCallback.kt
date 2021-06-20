package com.mockdroid.pekjetpek.ui.tvshow

import com.mockdroid.pekjetpek.data.MovieEntity

interface TvShowFragmentCallback {
    fun onShareClick(tvShow: MovieEntity)
}