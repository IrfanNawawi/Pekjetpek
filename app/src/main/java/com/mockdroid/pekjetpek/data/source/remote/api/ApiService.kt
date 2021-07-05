package com.mockdroid.pekjetpek.data.source.remote.api

import com.mockdroid.pekjetpek.BuildConfig
import com.mockdroid.pekjetpek.data.source.remote.response.movie.MovieItem
import com.mockdroid.pekjetpek.data.source.remote.response.movie.MovieResponse
import com.mockdroid.pekjetpek.data.source.remote.response.tvshow.TvShowItem
import com.mockdroid.pekjetpek.data.source.remote.response.tvshow.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun getMovies(@Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Call<MovieResponse>

    @GET("tv/on_the_air")
    fun getTvShows(@Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Call<TvShowResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<MovieItem>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvShowId: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<TvShowItem>
}