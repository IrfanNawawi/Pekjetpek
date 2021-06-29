package com.mockdroid.pekjetpek.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tvshowjet")
@Parcelize
data class TvShowEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "tvShowId")
    var movieId: Int = 0,

    @ColumnInfo(name = "tvShowName")
    var name: String? = null,

    @ColumnInfo(name = "tvShowOverview")
    var overview: String? = null,

    @ColumnInfo(name = "tvShowDate")
    var firstAirDate: String? = null,

    @ColumnInfo(name = "moviePoster")
    var posterPath: String? = null,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable