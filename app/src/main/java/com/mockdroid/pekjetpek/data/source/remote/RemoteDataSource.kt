package com.mockdroid.pekjetpek.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mockdroid.pekjetpek.data.source.remote.api.ApiConfig
import com.mockdroid.pekjetpek.data.source.remote.response.movie.MovieItem
import com.mockdroid.pekjetpek.data.source.remote.response.movie.MovieResponse
import com.mockdroid.pekjetpek.data.source.remote.response.tvshow.TvShowItem
import com.mockdroid.pekjetpek.data.source.remote.response.tvshow.TvShowResponse
import com.mockdroid.pekjetpek.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<List<MovieItem>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MovieItem>>>()
        val client = ApiConfig.getApiService().getMovies()

        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                resultMovies.value =
                    ApiResponse.success(response.body()?.results as List<MovieItem>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultMovies
    }

    fun getAllTvShows(): LiveData<ApiResponse<List<TvShowItem>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<TvShowItem>>>()
        val client = ApiConfig.getApiService().getTvShows()

        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                resultTvShows.value =
                    ApiResponse.success(response.body()?.results as List<TvShowItem>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvShows onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultTvShows
    }

    fun getMovieDetail(movieId: String): LiveData<ApiResponse<MovieItem>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<MovieItem>>()
        val client = ApiConfig.getApiService().getDetailMovie(movieId)

        client.enqueue(object : Callback<MovieItem> {
            override fun onResponse(call: Call<MovieItem>, response: Response<MovieItem>) {
                resultDetailMovie.value = ApiResponse.success(response.body() as MovieItem)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieItem>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailMovies onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultDetailMovie
    }

    fun getTvShowDetail(tvShowId: String): LiveData<ApiResponse<TvShowItem>> {
        EspressoIdlingResource.increment()
        val resultDetailTvShows = MutableLiveData<ApiResponse<TvShowItem>>()
        val client = ApiConfig.getApiService().getDetailTvShow(tvShowId)

        client.enqueue(object : Callback<TvShowItem> {
            override fun onResponse(call: Call<TvShowItem>, response: Response<TvShowItem>) {
                resultDetailTvShows.value = ApiResponse.success(response.body() as TvShowItem)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowItem>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailTvShows onFailure : ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })

        return resultDetailTvShows
    }
}