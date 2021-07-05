package com.mockdroid.pekjetpek.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity

@Dao
interface MovieTvShowDao {
    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM moviejet WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM moviejet WHERE isFavorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update(entity = MovieEntity::class)
    fun updateMovies(movie: MovieEntity)

    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getTvShows(query: SupportSQLiteQuery): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowjet WHERE tvShowId = :tvShowId")
    fun getDetailTvShowById(tvShowId: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM tvshowjet WHERE isFavorite = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update(entity = TvShowEntity::class)
    fun updateTvShows(tvShows: TvShowEntity)

}