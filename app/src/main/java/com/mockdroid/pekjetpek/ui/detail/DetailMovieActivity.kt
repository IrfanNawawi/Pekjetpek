package com.mockdroid.pekjetpek.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mockdroid.pekjetpek.R
import com.mockdroid.pekjetpek.data.MovieEntity
import com.mockdroid.pekjetpek.databinding.ActivityDetailMovieBinding
import com.mockdroid.pekjetpek.databinding.ContentDetailMovieBinding
import com.mockdroid.pekjetpek.utils.Const
import com.mockdroid.pekjetpek.utils.DataDummy

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailMovieBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailMovieBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailMovieViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(Const.EXTRA_MOVIE)
            val type = extras.getString(Const.EXTRA_TYPE)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                if (type == Const.TYPE_MOVIE) {
                    detailMovie(viewModel.getMovie())
                } else {
                    detailMovie(viewModel.getTvShow())
                }
            }
        }
    }

    private fun detailMovie(movieEntity: MovieEntity) {
        detailMovieBinding.textTitle.text = movieEntity.title
        detailMovieBinding.textDescription.text = movieEntity.description
        detailMovieBinding.textDate.text = movieEntity.releaseDate
        Glide.with(this).load(movieEntity.poster).transform(RoundedCorners(20)).apply(
            RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
        ).into(detailMovieBinding.imagePoster)
    }
}