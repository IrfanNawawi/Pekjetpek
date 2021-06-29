package com.mockdroid.pekjetpek.ui.tvshow

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
import com.mockdroid.pekjetpek.data.source.remote.response.TvShowItem
import com.mockdroid.pekjetpek.databinding.FragmentMovieBinding
import com.mockdroid.pekjetpek.databinding.FragmentTvShowBinding
import com.mockdroid.pekjetpek.ui.detail.DetailMovieActivity
import com.mockdroid.pekjetpek.utils.Const.Companion.EXTRA_MOVIETV
import com.mockdroid.pekjetpek.utils.Const.Companion.EXTRA_TYPE
import com.mockdroid.pekjetpek.utils.Const.Companion.TYPE_TVSHOW
import com.mockdroid.pekjetpek.viewmodel.ViewModelFactory

class TvShowFragment : Fragment(), TvShowFragmentCallback {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val tvShowAdapter = TvShowAdapter(this)
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getTvShow().observe(viewLifecycleOwner, Observer { movies ->
                binding.progressBar.visibility = View.GONE
                tvShowAdapter.setTvShow(movies)
                tvShowAdapter.notifyDataSetChanged()
            })

            with(binding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    override fun onDetailClick(tvShow: TvShowItem) {
        startActivity(
            Intent(context, DetailMovieActivity::class.java)
                .putExtra(EXTRA_MOVIETV, tvShow.id)
                .putExtra(EXTRA_TYPE, TYPE_TVSHOW)
        )
    }

    override fun onShareClick(tvShow: TvShowItem) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).setText(mimeType)
                .setChooserTitle("Bagikan tv show ini sekarang.")
                .setText(resources.getString(R.string.share_text, tvShow.name))
                .startChooser()
        }
    }

}