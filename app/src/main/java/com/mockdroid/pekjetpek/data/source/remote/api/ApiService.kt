package com.mockdroid.pekjetpek.data.source.remote.api

import com.mockdroid.pekjetpek.BuildConfig
import com.mockdroid.pekjetpek.data.source.remote.response.MovieItem
import com.mockdroid.pekjetpek.data.source.remote.response.MovieTvShowResponse
import com.mockdroid.pekjetpek.data.source.remote.response.TvShowItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Call<MovieTvShowResponse<MovieItem>>

    @GET("tv/on_the_air")
    fun getTvShowOnTheAir(@Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY): Call<MovieTvShowResponse<TvShowItem>>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<MovieItem>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<TvShowItem>
}