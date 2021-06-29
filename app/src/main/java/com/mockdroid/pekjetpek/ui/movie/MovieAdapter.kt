package com.mockdroid.pekjetpek.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mockdroid.pekjetpek.BuildConfig
import com.mockdroid.pekjetpek.R
import com.mockdroid.pekjetpek.data.source.remote.response.MovieItem
import com.mockdroid.pekjetpek.databinding.ItemsMovieBinding
import com.mockdroid.pekjetpek.utils.Const.Companion.POSTER_SIZE_W185


class MovieAdapter(private val callback: MovieFragmentCallback) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MovieItem>()

    fun setMovies(movies: List<MovieItem>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemsMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MovieViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieItem) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemDate.text = movie.releaseDate
                itemView.setOnClickListener { callback.onDetailClick(movie) }
                imgShare.setOnClickListener { callback.onShareClick(movie) }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL_TMDB + POSTER_SIZE_W185 + movie.posterPath).apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                ).into(imgPoster)
            }
        }
    }
}