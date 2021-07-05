package com.mockdroid.pekjetpek.ui.favorite.tvshow_fav

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mockdroid.pekjetpek.data.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity
import com.mockdroid.pekjetpek.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowFavoriteViewModelTest {

    private lateinit var viewModel: TvShowFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var favPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowFavoriteViewModel(movieTvShowRepository)
    }

    @Test
    fun getFavoriteTvShows() {
        val dummyTvShows = favPagedList
        `when`(dummyTvShows.size).thenReturn(1)
        val tvShow = MutableLiveData<PagedList<TvShowEntity>>()
        tvShow.value = dummyTvShows

        `when`(movieTvShowRepository.getFavoriteTvShows()).thenReturn(tvShow)
        val tvShowEntities = viewModel.getFavoriteTvShows().value
        verify(movieTvShowRepository).getFavoriteTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(1, tvShowEntities?.size)

        viewModel.getFavoriteTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }

    @Test
    fun setFavoriteTvShow() {
        viewModel.setFavoriteTvShow(DataDummy.getDummyDetailTvShow())
        verify(movieTvShowRepository).setFavoriteTvShow(DataDummy.getDummyDetailTvShow(), true)
        verifyNoMoreInteractions(movieTvShowRepository)
    }
}