package com.mockdroid.pekjetpek.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieItem(

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null
)