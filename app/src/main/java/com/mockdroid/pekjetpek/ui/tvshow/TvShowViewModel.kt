package com.mockdroid.pekjetpek.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mockdroid.pekjetpek.data.source.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.remote.response.TvShowItem

class TvShowViewModel(private val moviewTvShowRepository: MovieTvShowRepository) : ViewModel() {
    fun getTvShow(): LiveData<List<TvShowItem>> = moviewTvShowRepository.getAllTvShows()
}