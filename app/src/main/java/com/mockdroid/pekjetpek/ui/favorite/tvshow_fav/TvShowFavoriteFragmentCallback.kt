package com.mockdroid.pekjetpek.ui.favorite.tvshow_fav

import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity

interface TvShowFavoriteFragmentCallback {
    fun onDetailClick(tvShow: TvShowEntity)
    fun onShareClick(tvShow: TvShowEntity)
}