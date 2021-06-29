package com.mockdroid.pekjetpek.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mockdroid.pekjetpek.data.source.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.remote.response.TvShowItem
import com.mockdroid.pekjetpek.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowItem>>

    @Before
    fun setup() {
        viewModel = TvShowViewModel(movieTvShowRepository)
    }

    @Test
    fun getTvShow() {
        val dummyTvShows = DataDummy.generateDummyTvShow()
        val tvShows = MutableLiveData<List<TvShowItem>>()
        tvShows.value = dummyTvShows

        Mockito.`when`(movieTvShowRepository.getAllTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShow().value
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.size)

        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShows)
    }
}