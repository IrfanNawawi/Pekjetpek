package com.mockdroid.pekjetpek.ui.favorite.tvshow_fav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mockdroid.pekjetpek.BuildConfig
import com.mockdroid.pekjetpek.R
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity
import com.mockdroid.pekjetpek.databinding.ItemsMovieBinding
import com.mockdroid.pekjetpek.utils.Const

class TvShowFavoriteAdapter(private val callback: TvShowFavoriteFragmentCallback) :
    PagedListAdapter<TvShowEntity, TvShowFavoriteAdapter.TvShowFavoriteViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowFavoriteViewHolder {
        val itemTvShowBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowFavoriteViewHolder(itemTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowFavoriteViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class TvShowFavoriteViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.name
                tvItemDate.text = tvShow.firstAirDate
                itemView.setOnClickListener { callback.onDetailClick(tvShow) }
                imgShare.setOnClickListener { callback.onShareClick(tvShow) }
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL_TMDB + Const.POSTER_SIZE_W185 + tvShow.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    ).into(imgPoster)
            }
        }
    }
}