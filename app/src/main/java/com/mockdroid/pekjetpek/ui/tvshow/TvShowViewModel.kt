package com.mockdroid.pekjetpek.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mockdroid.pekjetpek.data.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity
import com.mockdroid.pekjetpek.vo.Resource

class TvShowViewModel(private val moviewTvShowRepository: MovieTvShowRepository) : ViewModel() {
    fun getTvShow(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> =
        moviewTvShowRepository.getAllTvShows(sort)
}