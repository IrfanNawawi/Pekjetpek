package com.mockdroid.pekjetpek.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mockdroid.pekjetpek.data.MovieTvShowRepository
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity
import com.mockdroid.pekjetpek.utils.Const.Companion.TYPE_MOVIE
import com.mockdroid.pekjetpek.utils.Const.Companion.TYPE_TVSHOW
import com.mockdroid.pekjetpek.utils.DataDummy
import com.mockdroid.pekjetpek.vo.Resource
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
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

    private val dummyMovie = DataDummy.getDummyDetailMovie()
    private val dummyTvShow = DataDummy.getDummyDetailTvShow()
    private val movieId = dummyMovie.movieId
    private val tvShowId = dummyTvShow.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvShowRepository: MovieTvShowRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setupMovie() {
        viewModel = DetailMovieViewModel(movieTvShowRepository)
    }

    @Test
    fun getMovieDetail() {
        val dummyDetailMovie = Resource.success(DataDummy.getDummyDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(movieTvShowRepository.getMovieDetail(movieId)).thenReturn(movie)

        viewModel.setSelectedTMDB(movieId.toString(), TYPE_MOVIE)
        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
        verify(movieTvShowRepository).getMovieDetail(movieId)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.getDummyDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(movieTvShowRepository.getMovieDetail(movieId)).thenReturn(movie)

        viewModel.setSelectedTMDB(movieId.toString(), TYPE_MOVIE)
        viewModel.setFavoriteMovie()
        verify(movieTvShowRepository)
            .setFavoriteMovie(movie.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(movieObserver)
    }

    @Before
    fun setupTvShow() {
        viewModel = DetailMovieViewModel(movieTvShowRepository)
    }

    @Test
    fun getTvShowDetail() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDummyDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(movieTvShowRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)

        viewModel.setSelectedTMDB(tvShowId.toString(), TYPE_TVSHOW)
        viewModel.getDetailTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
        verify(movieTvShowRepository).getTvShowDetail(tvShowId)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.getDummyDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(movieTvShowRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)

        viewModel.setSelectedTMDB(tvShowId.toString(), TYPE_TVSHOW)
        viewModel.setFavoriteTvShow()
        verify(movieTvShowRepository)
            .setFavoriteTvShow(tvShow.value!!.data as TvShowEntity, true)
        verifyNoMoreInteractions(tvShowObserver)
    }
}