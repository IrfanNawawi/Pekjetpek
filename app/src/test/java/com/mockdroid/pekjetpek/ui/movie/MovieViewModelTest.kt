package com.mockdroid.pekjetpek.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mockdroid.pekjetpek.data.source.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.remote.response.MovieItem
import com.mockdroid.pekjetpek.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var observer: Observer<List<MovieItem>>

    @Before
    fun setup() {
        viewModel = MovieViewModel(movieTvShowRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovies = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<List<MovieItem>>()
        movies.value = dummyMovies

        `when`(movieTvShowRepository.getAllMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovie().value
        verify(movieTvShowRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}