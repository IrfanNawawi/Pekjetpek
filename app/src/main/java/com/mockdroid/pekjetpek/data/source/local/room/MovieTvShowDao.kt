package com.mockdroid.pekjetpek.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity

@Dao
interface MovieTvShowDao {
    @Query("SELECT * FROM moviejet")
    fun getListMovies() : LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tvshowjet")
    fun getListTvShows() : LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM moviejet WHERE isFavorite = 1")
    fun getListFavoriteMovies() : LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tvshowjet WHERE isFavorite = 1")
    fun getListFavoriteTvShows() : LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM moviejet WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int) : LiveData<MovieEntity>

    @Query("SELECT * FROM tvshowjet WHERE tvShowId = :tvShowId")
    fun getDetailTvShowById(tvShowId: Int) : LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowEntity::class)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update(entity = MovieEntity::class)
    fun updateMovie(movie : MovieEntity)

    @Update(entity = TvShowEntity::class)
    fun updateTvShow(tvShows: TvShowEntity)

}