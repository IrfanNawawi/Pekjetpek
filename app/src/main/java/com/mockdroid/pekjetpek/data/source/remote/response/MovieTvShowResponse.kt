package com.mockdroid.pekjetpek.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieTvShowResponse<T>(
    @SerializedName("results")
    val result: List<T>? = null
)