package com.mockdroid.pekjetpek.utils

import com.mockdroid.pekjetpek.R

class Const {
    companion object {
        val TAB_TITLES = intArrayOf(R.string.movie, R.string.tvshow, R.string.favorite)

        const val TYPE_MOVIE = "movie"
        const val TYPE_TVSHOW = "tv"
        const val EXTRA_MOVIETV = "extra_movietv"
        const val EXTRA_TYPE = "extra_type"
        const val POSTER_SIZE_W185 = "w185"

        const val SORT_BEST = "Best"
        const val SORT_WORST = "Worst"
        const val SORT_RANDOM = "Random"
        const val MOVIE_TMDB = "moviejet"
        const val TVSHOW_TMDB = "tvshowjet"
    }
}