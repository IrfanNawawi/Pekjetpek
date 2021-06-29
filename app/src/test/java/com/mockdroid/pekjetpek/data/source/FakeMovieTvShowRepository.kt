package com.mockdroid.pekjetpek.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mockdroid.pekjetpek.data.source.remote.RemoteDataSource
import com.mockdroid.pekjetpek.data.source.remote.response.MovieItem
import com.mockdroid.pekjetpek.data.source.remote.response.TvShowItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeMovieTvShowRepository(private val remoteDataSource: RemoteDataSource) :
    MovieTvShowDataSource {

    override fun getAllMovies(): LiveData<List<MovieItem>> {
        val movieResults = MutableLiveData<List<MovieItem>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
                override fun onAllMoviesReceived(movieResponse: List<MovieItem>) {
                    val movieList = ArrayList<MovieItem>()
                    for (response in movieResponse) {
                        val movie = MovieItem(
                            response.id,
                            response.title,
                            response.overview,
                            response.releaseDate,
                            response.posterPath
                        )
                        movieList.add(movie)
                    }
                    movieResults.postValue(movieList)
                }
            })
        }
        return movieResults
    }

    override fun getAllTvShows(): LiveData<List<TvShowItem>> {
        val tvShowResults = MutableLiveData<List<TvShowItem>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowCallback {
                override fun onAllTvShowReceived(movieResponse: List<TvShowItem>) {
                    val tvShowList = ArrayList<TvShowItem>()
                    for (response in movieResponse) {
                        val tvShow = TvShowItem(
                            response.id,
                            response.name,
                            response.overview,
                            response.firstAirDate,
                            response.posterPath
                        )
                        tvShowList.add(tvShow)
                    }
                    tvShowResults.postValue(tvShowList)
                }

            })
        }
        return tvShowResults
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieItem> {
        val movieResults = MutableLiveData<MovieItem>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieDetail(
                movieId,
                object : RemoteDataSource.LoadMovieDetailCallback {
                    override fun onMovieDetailReceived(movieResponse: MovieItem) {
                        val movie = MovieItem(
                            movieResponse.id,
                            movieResponse.title,
                            movieResponse.overview,
                            movieResponse.releaseDate,
                            movieResponse.posterPath
                        )
                        movieResults.postValue(movie)
                    }
                })
        }
        return movieResults
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvShowItem> {
        val tvShowResults = MutableLiveData<TvShowItem>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShowDetail(
                tvShowId,
                object : RemoteDataSource.LoadTvShowDetailCallback {
                    override fun onTvShowDetailReceived(response: TvShowItem) {
                        val tvShow = TvShowItem(
                            response.id,
                            response.name,
                            response.overview,
                            response.firstAirDate,
                            response.posterPath
                        )
                        tvShowResults.postValue(tvShow)
                    }

                })
        }
        return tvShowResults
    }

}