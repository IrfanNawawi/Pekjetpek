package com.mockdroid.pekjetpek.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mockdroid.pekjetpek.data.MovieTvShowDataSource
import com.mockdroid.pekjetpek.data.NetworkBoundResource
import com.mockdroid.pekjetpek.data.source.local.LocalDataSource
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity
import com.mockdroid.pekjetpek.data.source.remote.ApiResponse
import com.mockdroid.pekjetpek.data.source.remote.RemoteDataSource
import com.mockdroid.pekjetpek.data.source.remote.response.movie.MovieItem
import com.mockdroid.pekjetpek.data.source.remote.response.tvshow.TvShowItem
import com.mockdroid.pekjetpek.utils.AppExecutors
import com.mockdroid.pekjetpek.vo.Resource

class FakeMovieTvShowRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MovieTvShowDataSource {

    override fun getAllMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieItem>>(
                appExecutors
            ) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(data: List<MovieItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (i in data.indices) {
                    val response = data[i]
                    val movie = MovieEntity(
                        null,
                        response.id,
                        response.title,
                        response.overview,
                        response.releaseDate,
                        response.posterPath,
                        response.voteAverage,
                        false
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieItem>(appExecutors) {
            public override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieDetail(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null

            public override fun createCall(): LiveData<ApiResponse<MovieItem>> =
                remoteDataSource.getMovieDetail(movieId.toString())

            public override fun saveCallResult(data: MovieItem) {
                val movie = MovieEntity(
                    null,
                    data.id,
                    data.title,
                    data.overview,
                    data.releaseDate,
                    data.posterPath,
                    data.voteAverage,
                    false
                )
                localDataSource.updateMovie(movie, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        localDataSource.setFavoriteMovie(movie, state)
    }

    override fun getAllTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowItem>>> =
                remoteDataSource.getAllTvShows()

            public override fun saveCallResult(data: List<TvShowItem>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (i in data.indices) {
                    val response = data[i]
                    val tvShow = TvShowEntity(
                        null,
                        response.id,
                        response.name,
                        response.overview,
                        response.firstAirDate,
                        response.posterPath,
                        response.voteAverage,
                        false
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowItem>(appExecutors) {
            public override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowDetail(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null

            public override fun createCall(): LiveData<ApiResponse<TvShowItem>> =
                remoteDataSource.getTvShowDetail(tvShowId.toString())

            public override fun saveCallResult(data: TvShowItem) {
                val tvShow = TvShowEntity(
                    null,
                    data.id,
                    data.name,
                    data.overview,
                    data.firstAirDate,
                    data.posterPath,
                    data.voteAverage,
                    false
                )
                localDataSource.updateTvShow(tvShow, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        localDataSource.setFavoriteTvShow(tvShow, state)
    }

}