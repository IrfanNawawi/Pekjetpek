package com.mockdroid.pekjetpek.ui.favorite.movie_fav

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
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.databinding.FragmentMovieFavoriteBinding
import com.mockdroid.pekjetpek.ui.detail.DetailMovieActivity
import com.mockdroid.pekjetpek.utils.Const
import com.mockdroid.pekjetpek.viewmodel.ViewModelFactory

class MovieFavoriteFragment : Fragment(), MovieFavoriteFragmentCallback {
    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: MovieFavoriteViewModel
    private lateinit var favoriteAdapter: MovieFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movieFav ->
            if (movieFav != null) {
                favoriteAdapter.submitList(movieFav)
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding?.rvFavMovies)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]

            favoriteAdapter = MovieFavoriteAdapter(this)
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movieFav ->
                binding?.progressBar?.visibility = View.GONE
                favoriteAdapter.submitList(movieFav)
            })

            binding?.rvFavMovies?.layoutManager = LinearLayoutManager(context)
            binding?.rvFavMovies?.setHasFixedSize(true)
            binding?.rvFavMovies?.adapter = favoriteAdapter
        }
    }

    override fun onDetailClick(movie: MovieEntity) {
        startActivity(
            Intent(context, DetailMovieActivity::class.java)
                .putExtra(Const.EXTRA_MOVIETV, movie.movieId.toString())
                .putExtra(Const.EXTRA_TYPE, Const.TYPE_MOVIE)
        )
    }

    override fun onShareClick(movie: MovieEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).setText(mimeType)
                .setChooserTitle("Bagikan tv show ini sekarang.")
                .setText(resources.getString(R.string.share_text, movie.title))
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
            tvShowEntity?.let { viewModel.setFavoriteMovie(it) }

            val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
            snackbar.setAction(R.string.message_ok) { view ->
                tvShowEntity?.let { viewModel.setFavoriteMovie(it) }
            }
            snackbar.show()
        }
    })

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}