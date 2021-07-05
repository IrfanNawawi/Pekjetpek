package com.mockdroid.pekjetpek.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowjet")
data class TvShowEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: Int = 0,

    @ColumnInfo(name = "tvShowName")
    var name: String? = null,

    @ColumnInfo(name = "tvShowOverview")
    var overview: String? = null,

    @ColumnInfo(name = "tvShowDate")
    var firstAirDate: String? = null,

    @ColumnInfo(name = "tvShowPoster")
    var posterPath: String? = null,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double? = null,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)