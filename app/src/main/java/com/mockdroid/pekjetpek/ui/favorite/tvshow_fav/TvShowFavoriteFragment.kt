package com.mockdroid.pekjetpek.ui.favorite.tvshow_fav

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mockdroid.pekjetpek.R
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity
import com.mockdroid.pekjetpek.databinding.FragmentTvShowFavoriteBinding
import com.mockdroid.pekjetpek.ui.detail.DetailMovieActivity
import com.mockdroid.pekjetpek.utils.Const
import com.mockdroid.pekjetpek.viewmodel.ViewModelFactory

class TvShowFavoriteFragment : Fragment(), TvShowFavoriteFragmentCallback {
    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: TvShowFavoriteViewModel
    private lateinit var favoriteAdapter: TvShowFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { tvShowFav ->
            if (tvShowFav != null) {
                favoriteAdapter.submitList(tvShowFav)
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding?.rvFavTvShow)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]

            favoriteAdapter = TvShowFavoriteAdapter(this)
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { tvShowFav ->
                binding?.progressBar?.visibility = View.GONE
                favoriteAdapter.submitList(tvShowFav)
            })

            binding?.rvFavTvShow?.layoutManager = LinearLayoutManager(context)
            binding?.rvFavTvShow?.setHasFixedSize(true)
            binding?.rvFavTvShow?.adapter = favoriteAdapter
        }
    }

    override fun onDetailClick(tvShow: TvShowEntity) {
        startActivity(
            Intent(context, DetailMovieActivity::class.java)
                .putExtra(Const.EXTRA_MOVIETV, tvShow.tvShowId.toString())
                .putExtra(Const.EXTRA_TYPE, Const.TYPE_TVSHOW)
        )
    }

    override fun onShareClick(tvShow: TvShowEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).setText(mimeType)
                .setChooserTitle("Bagikan tv show ini sekarang.")
                .setText(resources.getString(R.string.share_text, tvShow.name))
                .startChooser()
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val swipedPosition = viewHolder.adapterPosition
            val tvShowEntity = favoriteAdapter.getSwipedData(swipedPosition)
            tvShowEntity?.let { viewModel.setFavoriteTvShow(it) }

            val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
            snackbar.setAction(R.string.message_ok) { view ->
                tvShowEntity?.let { viewModel.setFavoriteTvShow(it) }
            }
            snackbar.show()
        }
    })

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}