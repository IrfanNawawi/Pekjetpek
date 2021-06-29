package com.mockdroid.pekjetpek.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mockdroid.pekjetpek.BuildConfig
import com.mockdroid.pekjetpek.R
import com.mockdroid.pekjetpek.data.source.remote.response.MovieItem
import com.mockdroid.pekjetpek.data.source.remote.response.TvShowItem
import com.mockdroid.pekjetpek.databinding.ActivityDetailMovieBinding
import com.mockdroid.pekjetpek.databinding.ContentDetailMovieBinding
import com.mockdroid.pekjetpek.utils.Const
import com.mockdroid.pekjetpek.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    private var _binding: ContentDetailMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        _binding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val movieId = intent.getIntExtra(Const.EXTRA_MOVIETV, 0)
        val type = intent.getStringExtra(Const.EXTRA_TYPE)
        binding.progressBar.visibility = View.VISIBLE
        if (type == Const.TYPE_MOVIE) {
            viewModel.getMovieDetail(movieId).observe(this, { movie ->
                binding.progressBar.visibility = View.GONE
                detailMovie(movie)
            })
        } else {
            viewModel.getTvShowDetail(movieId).observe(this, { tvShow ->
                binding.progressBar.visibility = View.GONE
                detailTvShow(tvShow)
            })
        }
    }

    private fun detailMovie(movieDetail: MovieItem) {
        binding.textTitle.text = movieDetail.title
        binding.textDescription.text = movieDetail.overview
        binding.textDate.text = movieDetail.releaseDate
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL_TMDB + Const.POSTER_SIZE_W185 + movieDetail.posterPath)
            .transform(RoundedCorners(20)).apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
            ).into(binding.imagePoster)
    }

    private fun detailTvShow(tvShowDetail: TvShowItem) {
        binding.textTitle.text = tvShowDetail.name
        binding.textDescription.text = tvShowDetail.overview
        binding.textDate.text = tvShowDetail.firstAirDate
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL_TMDB + Const.POSTER_SIZE_W185 + tvShowDetail.posterPath)
            .transform(RoundedCorners(20)).apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
            ).into(binding.imagePoster)
    }
}