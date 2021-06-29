package com.mockdroid.pekjetpek.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MovieTvShowDatabase : RoomDatabase() {
    abstract fun movieTvShow(): MovieTvShowDao

    companion object {
        @Volatile
        private var INSTANCE: MovieTvShowDatabase? = null

        fun getInstance(context: Context): MovieTvShowDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MovieTvShowDatabase::class.java,
                    "movietvshowjet.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }

}