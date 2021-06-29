package com.mockdroid.pekjetpek.ui.tvshow

import com.mockdroid.pekjetpek.data.source.remote.response.TvShowItem

interface TvShowFragmentCallback {
    fun onDetailClick(tvShow: TvShowItem)
    fun onShareClick(tvShow: TvShowItem)
}