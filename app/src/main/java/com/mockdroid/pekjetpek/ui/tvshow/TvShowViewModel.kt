package com.mockdroid.pekjetpek.ui.tvshow

import androidx.lifecycle.ViewModel
import com.mockdroid.pekjetpek.data.MovieEntity
import com.mockdroid.pekjetpek.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShow(): List<MovieEntity> = DataDummy.generateDummyTvShow()
}