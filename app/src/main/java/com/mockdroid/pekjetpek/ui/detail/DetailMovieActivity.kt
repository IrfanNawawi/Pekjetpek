package com.mockdroid.pekjetpek.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mockdroid.pekjetpek.BuildConfig
import com.mockdroid.pekjetpek.R
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity
import com.mockdroid.pekjetpek.databinding.ActivityDetailMovieBinding
import com.mockdroid.pekjetpek.databinding.ContentDetailMovieBinding
import com.mockdroid.pekjetpek.utils.Const
import com.mockdroid.pekjetpek.utils.Const.Companion.EXTRA_MOVIETV
import com.mockdroid.pekjetpek.utils.Const.Companion.EXTRA_TYPE
import com.mockdroid.pekjetpek.utils.Const.Companion.TYPE_MOVIE
import com.mockdroid.pekjetpek.utils.Const.Companion.TYPE_TVSHOW
import com.mockdroid.pekjetpek.viewmodel.ViewModelFactory
import com.mockdroid.pekjetpek.vo.Status

class DetailMovieActivity : AppCompatActivity() {
    private var _binding: ContentDetailMovieBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: DetailMovieViewModel
    private var menu: Menu? = null
    private var dataCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        _binding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieTvShowId = extras.getString(EXTRA_MOVIETV)
            dataCategory = extras.getString(EXTRA_TYPE)

            if (movieTvShowId != null && dataCategory != null) {
                viewModel.setSelectedTMDB(movieTvShowId, dataCategory.toString())
                setupState()
                if (dataCategory == TYPE_MOVIE) {
                    viewModel.getDetailMovie().observe(this, { movie ->
                        if (movie != null) {
                            when (movie.status) {
                                Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                                Status.SUCCESS -> {
                                    if (movie.data != null) {
                                        binding?.progressBar?.visibility = View.GONE
                                        detailMovie(movie.data)
                                    }
                                }
                                Status.ERROR -> {
                                    binding?.progressBar?.visibility = View.GONE
                                    Toast.makeText(
                                        applicationContext,
                                        "Terjadi kesalahan",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    })
                } else {
                    viewModel.getDetailTvShow().observe(this, { tvShow ->
                        if (tvShow != null) {
                            when (tvShow.status) {
                                Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                                Status.SUCCESS -> {
                                    if (tvShow.data != null) {
                                        binding?.progressBar?.visibility = View.GONE
                                        detailTvShow(tvShow.data)
                                    }
                                }
                                Status.ERROR -> {
                                    binding?.progressBar?.visibility = View.GONE
                                    Toast.makeText(
                                        applicationContext,
                                        "Terjadi kesalahan",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    })
                }
            }
        }
    }

    private fun setupState() {
        if (dataCategory == TYPE_MOVIE) {
            viewModel.getDetailMovie().observe(this, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            if (movie.data != null) {
                                binding?.progressBar?.visibility = View.GONE
                                val state = movie.data.isFavorite
                                setFavoriteState(state)
                            }
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        } else {
            viewModel.getDetailTvShow().observe(this, { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            if (tvShow.data != null) {
                                binding?.progressBar?.visibility = View.GONE
                                val state = tvShow.data.isFavorite
                                setFavoriteState(state)
                            }
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }
    }

    private fun detailMovie(movieDetail: MovieEntity) {
        binding?.textTitle?.text = movieDetail.title
        binding?.textDescription?.text = movieDetail.overview
        binding?.textDate?.text = movieDetail.releaseDate
        binding?.imagePoster?.let { image ->
            Glide.with(this)
                .load(BuildConfig.IMAGE_URL_TMDB + Const.POSTER_SIZE_W185 + movieDetail.posterPath)
                .transform(RoundedCorners(20)).apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                ).into(image)
        }
    }

    private fun detailTvShow(tvShowDetail: TvShowEntity) {
        binding?.textTitle?.text = tvShowDetail.name
        binding?.textDescription?.text = tvShowDetail.overview
        binding?.textDate?.text = tvShowDetail.firstAirDate
        binding?.imagePoster?.let { image ->
            Glide.with(this)
                .load(BuildConfig.IMAGE_URL_TMDB + Const.POSTER_SIZE_W185 + tvShowDetail.posterPath)
                .transform(RoundedCorners(20)).apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                ).into(image)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        if (dataCategory == TYPE_MOVIE) {
            viewModel.getDetailMovie().observe(this, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            if (movie.data != null) {
                                binding?.progressBar?.visibility = View.GONE
                                val state = movie.data.isFavorite
                                setFavoriteState(state)
                            }
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        } else {
            viewModel.getDetailTvShow().observe(this, { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            if (tvShow.data != null) {
                                binding?.progressBar?.visibility = View.GONE
                                val state = tvShow.data.isFavorite
                                setFavoriteState(state)
                            }
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            onFavoriteClick()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onFavoriteClick() {
        if (dataCategory == TYPE_MOVIE) {
            viewModel.setFavoriteMovie()
        } else if (dataCategory == TYPE_TVSHOW) {
            viewModel.setFavoriteTvShow()
        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorited_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
        }
    }

}