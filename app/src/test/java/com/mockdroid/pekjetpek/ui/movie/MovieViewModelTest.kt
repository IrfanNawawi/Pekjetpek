package com.mockdroid.pekjetpek.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mockdroid.pekjetpek.data.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.vo.Resource
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
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Before
    fun setup() {
        viewModel = MovieViewModel(movieTvShowRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovies = Resource.success(moviePagedList)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(movieTvShowRepository.getAllMovies("BEST")).thenReturn(movies)
        val movieEntities = viewModel.getMovie("BEST").value?.data
        verify(movieTvShowRepository).getAllMovies("BEST")
        assertNotNull(movieEntities)
        assertEquals(0, movieEntities?.size)

        viewModel.getMovie("BEST").observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}