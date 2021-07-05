package com.mockdroid.pekjetpek.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.mockdroid.pekjetpek.R
import com.mockdroid.pekjetpek.data.source.local.entity.MovieEntity
import com.mockdroid.pekjetpek.databinding.FragmentMovieBinding
import com.mockdroid.pekjetpek.ui.detail.DetailMovieActivity
import com.mockdroid.pekjetpek.utils.Const
import com.mockdroid.pekjetpek.utils.Const.Companion.EXTRA_MOVIETV
import com.mockdroid.pekjetpek.utils.Const.Companion.EXTRA_TYPE
import com.mockdroid.pekjetpek.utils.Const.Companion.SORT_BEST
import com.mockdroid.pekjetpek.utils.Const.Companion.TYPE_MOVIE
import com.mockdroid.pekjetpek.viewmodel.ViewModelFactory
import com.mockdroid.pekjetpek.vo.Resource
import com.mockdroid.pekjetpek.vo.Status

class MovieFragment : Fragment(), MovieFragmentCallback {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieAdapter = MovieAdapter(this)
            viewModel.getMovie(SORT_BEST).observe(viewLifecycleOwner, movieObserver)

            with(binding?.rvMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }

    private val movieObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding?.progressBar?.visibility = View.GONE
                    movieAdapter.submitList(movies.data)
                    movieAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_best -> sort = SORT_BEST
            R.id.action_worst -> sort = Const.SORT_WORST
            R.id.action_random -> sort = Const.SORT_RANDOM
        }

        viewModel.getMovie(sort).observe(viewLifecycleOwner, movieObserver)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }

    override fun onDetailClick(movie: MovieEntity) {
        startActivity(
            Intent(context, DetailMovieActivity::class.java)
                .putExtra(EXTRA_MOVIETV, movie.movieId.toString())
                .putExtra(EXTRA_TYPE, TYPE_MOVIE)
        )
    }

    override fun onShareClick(movie: MovieEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).setText(mimeType)
                .setChooserTitle("Bagikan film ini sekarang.")
                .setText(resources.getString(R.string.share_text, movie.title))
                .startChooser()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}