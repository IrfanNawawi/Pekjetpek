package com.mockdroid.pekjetpek.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowItem(

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("voteAverage")
    var voteAverage: Double
)