package com.mockdroid.pekjetpek.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviejet")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int = 0,

    @ColumnInfo(name = "movieTitle")
    var title: String? = null,

    @ColumnInfo(name = "movieOverview")
    var overview: String? = null,

    @ColumnInfo(name = "movieDate")
    var releaseDate: String? = null,

    @ColumnInfo(name = "moviePoster")
    var posterPath: String? = null,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double? = null,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)