package com.mockdroid.pekjetpek.ui.tvshow

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
import com.mockdroid.pekjetpek.data.source.local.entity.TvShowEntity
import com.mockdroid.pekjetpek.databinding.FragmentTvShowBinding
import com.mockdroid.pekjetpek.ui.detail.DetailMovieActivity
import com.mockdroid.pekjetpek.utils.Const.Companion.EXTRA_MOVIETV
import com.mockdroid.pekjetpek.utils.Const.Companion.EXTRA_TYPE
import com.mockdroid.pekjetpek.utils.Const.Companion.SORT_BEST
import com.mockdroid.pekjetpek.utils.Const.Companion.SORT_RANDOM
import com.mockdroid.pekjetpek.utils.Const.Companion.SORT_WORST
import com.mockdroid.pekjetpek.utils.Const.Companion.TYPE_TVSHOW
import com.mockdroid.pekjetpek.viewmodel.ViewModelFactory
import com.mockdroid.pekjetpek.vo.Resource
import com.mockdroid.pekjetpek.vo.Status

class TvShowFragment : Fragment(), TvShowFragmentCallback {
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            tvShowAdapter = TvShowAdapter(this)
            viewModel.getTvShow(SORT_BEST).observe(viewLifecycleOwner, tvShowObserver)

            with(binding?.rvTvshow) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowAdapter
            }
        }
    }

    private val tvShowObserver = Observer<Resource<PagedList<TvShowEntity>>> { tvShows ->
        if (tvShows != null) {
            when (tvShows.status) {
                Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding?.progressBar?.visibility = View.GONE
                    tvShowAdapter.submitList(tvShows.data)
                    tvShowAdapter.notifyDataSetChanged()
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
            R.id.action_worst -> sort = SORT_WORST
            R.id.action_random -> sort = SORT_RANDOM
        }

        viewModel.getTvShow(sort).observe(viewLifecycleOwner, tvShowObserver)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }

    override fun onDetailClick(tvShow: TvShowEntity) {
        startActivity(
            Intent(context, DetailMovieActivity::class.java)
                .putExtra(EXTRA_MOVIETV, tvShow.tvShowId.toString())
                .putExtra(EXTRA_TYPE, TYPE_TVSHOW)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}