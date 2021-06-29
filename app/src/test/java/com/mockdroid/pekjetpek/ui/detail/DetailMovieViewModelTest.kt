package com.mockdroid.pekjetpek.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mockdroid.pekjetpek.data.source.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.remote.response.MovieItem
import com.mockdroid.pekjetpek.data.source.remote.response.TvShowItem
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
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShow()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieItem>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowItem>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieTvShowRepository)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<MovieItem>()
        movie.value = dummyMovie

        `when`(movieTvShowRepository.getMovieDetail(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovieDetail(movieId).value
        verify(movieTvShowRepository).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity?.id)
        assertEquals(dummyMovie.title, movieEntity?.title)
        assertEquals(dummyMovie.overview, movieEntity?.overview)
        assertEquals(dummyMovie.releaseDate, movieEntity?.releaseDate)
        assertEquals(dummyMovie.originalLanguage, movieEntity?.originalLanguage)

        viewModel.getMovieDetail(movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShowItem>()
        tvShow.value = dummyTvShow

        `when`(movieTvShowRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShowDetail(tvShowId).value
        verify(movieTvShowRepository).getTvShowDetail(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity?.id)
        assertEquals(dummyTvShow.name, tvShowEntity?.name)
        assertEquals(dummyTvShow.overview, tvShowEntity?.overview)
        assertEquals(dummyTvShow.firstAirDate, tvShowEntity?.firstAirDate)
        assertEquals(dummyTvShow.originalLanguage, tvShowEntity?.originalLanguage)

        viewModel.getTvShowDetail(tvShowId).observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}