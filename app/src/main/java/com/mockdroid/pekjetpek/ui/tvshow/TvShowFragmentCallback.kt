package com.mockdroid.pekjetpek.ui.tvshow

import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity

interface TvShowFragmentCallback {
    fun onDetailClick(tvShow: TvShowEntity)
    fun onShareClick(tvShow: TvShowEntity)
}