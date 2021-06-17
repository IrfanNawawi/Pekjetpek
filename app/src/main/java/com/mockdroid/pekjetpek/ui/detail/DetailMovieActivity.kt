package com.mockdroid.pekjetpek.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mockdroid.pekjetpek.R

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}