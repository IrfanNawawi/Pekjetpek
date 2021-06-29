package com.mockdroid.pekjetpek.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mockdroid.pekjetpek.data.source.remote.RemoteDataSource
import com.mockdroid.pekjetpek.utils.DataDummy
import com.mockdroid.pekjetpek.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieTvShowRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieTvShowRepository = FakeMovieTvShowRepository(remote)

    private val listMovieResponse = DataDummy.generateDummyMovies()
    private val movieId = listMovieResponse[0].id
    private val listTvShowResponse = DataDummy.generateDummyTvShow()
    private val tvShowId = listTvShowResponse[0].id
    private val movieResponse = DataDummy.generateDummyMovies()[0]
    private val tvShowResponse = DataDummy.generateDummyTvShow()[0]

    @Test
    fun getMovies() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(
                    listMovieResponse
                )
                null
            }.`when`(remote).getAllMovies(any())
        }

        val data = LiveDataTestUtil.getValue(movieTvShowRepository.getAllMovies())

        runBlocking {
            verify(remote).getAllMovies(any())
        }

        assertNotNull(data)
        assertEquals(listMovieResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback).onMovieDetailReceived(
                    movieResponse
                )
                null
            }.`when`(remote).getMovieDetail(eq(movieId), any())
        }

        val data = LiveDataTestUtil.getValue(movieTvShowRepository.getMovieDetail(movieId))

        runBlocking {
            verify(remote).getMovieDetail(eq(movieId), any())
        }

        assertNotNull(data)
        assertEquals(movieResponse.id, data.id)
    }

    @Test
    fun getTvShows() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback).onAllTvShowReceived(
                    listTvShowResponse
                )
                null
            }.`when`(remote).getAllTvShows(any())
        }

        val data = LiveDataTestUtil.getValue(movieTvShowRepository.getAllTvShows())

        runBlocking {
            verify(remote).getAllTvShows(any())
        }

        assertNotNull(data)
        assertEquals(listTvShowResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getTvShowDetail() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback).onTvShowDetailReceived(
                    tvShowResponse
                )
                null
            }.`when`(remote).getTvShowDetail(eq(tvShowId), any())
        }

        val data = LiveDataTestUtil.getValue(movieTvShowRepository.getTvShowDetail(tvShowId))

        runBlocking {
            verify(remote).getTvShowDetail(eq(tvShowId), any())
        }

        assertNotNull(data)
        assertEquals(tvShowResponse.id, data.id)
    }
}