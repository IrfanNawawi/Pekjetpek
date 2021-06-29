package com.mockdroid.pekjetpek.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mockdroid.pekjetpek.R
import com.mockdroid.pekjetpek.data.source.remote.response.MovieItem
import com.mockdroid.pekjetpek.databinding.FragmentMovieBinding
import com.mockdroid.pekjetpek.ui.detail.DetailMovieActivity
import com.mockdroid.pekjetpek.utils.Const
import com.mockdroid.pekjetpek.viewmodel.ViewModelFactory

class MovieFragment : Fragment(), MovieFragmentCallback {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter(this)

            binding.progressBar.visibility = View.VISIBLE
            viewModel.getMovie().observe(viewLifecycleOwner, Observer { movies ->
                binding.progressBar.visibility = View.GONE
                movieAdapter.setMovies(movies)
                movieAdapter.notifyDataSetChanged()
            })

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDetailClick(movie: MovieItem) {
        startActivity(
            Intent(context, DetailMovieActivity::class.java)
                .putExtra(Const.EXTRA_MOVIETV, movie.id)
                .putExtra(Const.EXTRA_TYPE, Const.TYPE_MOVIE)
        )
    }

    override fun onShareClick(movie: MovieItem) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).setText(mimeType)
                .setChooserTitle("Bagikan film ini sekarang.")
                .setText(resources.getString(R.string.share_text, movie.title))
                .startChooser()
        }
    }
}