package com.mockdroid.pekjetpek.ui.movie

import androidx.lifecycle.ViewModel
import com.mockdroid.pekjetpek.data.MovieEntity
import com.mockdroid.pekjetpek.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovie(): List<MovieEntity> = DataDummy.generateDummyMovies()
}