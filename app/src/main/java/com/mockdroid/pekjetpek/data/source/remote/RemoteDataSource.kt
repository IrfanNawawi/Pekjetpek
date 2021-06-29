package com.mockdroid.pekjetpek.data.source.remote

import com.mockdroid.pekjetpek.data.source.remote.api.ApiConfig
import com.mockdroid.pekjetpek.data.source.remote.response.MovieItem
import com.mockdroid.pekjetpek.data.source.remote.response.TvShowItem
import com.mockdroid.pekjetpek.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    suspend fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getNowPlayingMovies().await().result?.let { listMovie ->
            callback.onAllMoviesReceived(listMovie)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getAllTvShows(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getTvShowOnTheAir().await().result?.let { listTvShow ->
            callback.onAllTvShowReceived(listTvShow)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getDetailMovie(movieId).await().let { movie ->
            callback.onMovieDetailReceived(movie)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getDetailTvShow(tvShowId).await().let { tvShow ->
            callback.onTvShowDetailReceived(tvShow)
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieItem>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(movieResponse: List<TvShowItem>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: MovieItem)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(response: TvShowItem)
    }
}