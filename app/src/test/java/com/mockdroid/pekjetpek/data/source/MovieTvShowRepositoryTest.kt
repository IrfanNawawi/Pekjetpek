package com.mockdroid.pekjetpek.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mockdroid.pekjetpek.data.source.local.LocalDataSource
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity
import com.mockdroid.pekjetpek.data.source.remote.RemoteDataSource
import com.mockdroid.pekjetpek.utils.AppExecutors
import com.mockdroid.pekjetpek.utils.DataDummy
import com.mockdroid.pekjetpek.utils.LiveDataTestUtil
import com.mockdroid.pekjetpek.utils.PagedListUtil
import com.mockdroid.pekjetpek.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieTvShowRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieTvShowRepository = FakeMovieTvShowRepository(remote, local, appExecutors)

    private val listMovieResponse = DataDummy.generateDummyMovies()
    private val movieDetailResponse = DataDummy.getDummyDetailMovie()
    private val movieId = listMovieResponse[0].movieId

    private val listTvShowResponse = DataDummy.generateDummyTvShow()
    private val tvShowDetailResponse = DataDummy.getDummyDetailTvShow()
    private val tvShowId = listTvShowResponse[0].tvShowId

    @Test
    fun getMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies("BEST")).thenReturn(dataSourceFactory)
        movieTvShowRepository.getAllMovies("BEST")

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies("BEST")
        assertNotNull(movieEntities.data)
        assertEquals(listMovieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val movieDetail = MutableLiveData<MovieEntity>()
        movieDetail.value = DataDummy.getDummyDetailMovie()
        `when`(local.getMovieDetail(movieId)).thenReturn(movieDetail)

        val movieEntities = LiveDataTestUtil.getValue(movieTvShowRepository.getMovieDetail(movieId))
        verify(local).getMovieDetail(movieId)
        assertNotNull(movieEntities.data)
        assertEquals(movieDetailResponse.movieId, movieEntities.data?.movieId)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        movieTvShowRepository.getFavoriteMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(listMovieResponse.size, movieEntities.data?.size)
    }

    @Test
    fun setFavoriteMovie() {
        movieTvShowRepository.setFavoriteMovie(DataDummy.getDummyDetailMovie(), true)
        verify(local).setFavoriteMovie(DataDummy.getDummyDetailMovie(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows("BEST")).thenReturn(dataSourceFactory)
        movieTvShowRepository.getAllTvShows("BEST")

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getAllTvShows("BEST")
        assertNotNull(tvShowEntities.data)
        assertEquals(listTvShowResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShowDetail() {
        val tvShowDetail = MutableLiveData<TvShowEntity>()
        tvShowDetail.value = DataDummy.getDummyDetailTvShow()
        `when`(local.getTvShowDetail(tvShowId)).thenReturn(tvShowDetail)

        val tvShowEntities =
            LiveDataTestUtil.getValue(movieTvShowRepository.getTvShowDetail(tvShowId))
        verify(local).getTvShowDetail(tvShowId)
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowDetailResponse.tvShowId, tvShowEntities.data?.tvShowId)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        movieTvShowRepository.getFavoriteTvShows()

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getFavoriteTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(listTvShowResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun setFavoriteTvShow() {
        movieTvShowRepository.setFavoriteTvShow(DataDummy.getDummyDetailTvShow(), true)
        verify(local).setFavoriteTvShow(DataDummy.getDummyDetailTvShow(), true)
        verifyNoMoreInteractions(local)
    }
}