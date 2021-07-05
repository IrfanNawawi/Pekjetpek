package com.mockdroid.pekjetpek.ui.favorite.movie_fav

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mockdroid.pekjetpek.data.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
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
class MovieFavoriteViewModelTest {

    private lateinit var viewModel: MovieFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var favPagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieFavoriteViewModel(movieTvShowRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovies = favPagedList
        `when`(dummyMovies.size).thenReturn(1)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovies

        `when`(movieTvShowRepository.getFavoriteMovies()).thenReturn(movie)
        val movieEntities = viewModel.getFavoriteMovies().value
        verify(movieTvShowRepository).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(1, movieEntities?.size)

        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun setFavoriteMovie() {
        viewModel.setFavoriteMovie(DataDummy.getDummyDetailMovie())
        verify(movieTvShowRepository).setFavoriteMovie(DataDummy.getDummyDetailMovie(), true)
        verifyNoMoreInteractions(movieTvShowRepository)
    }
}