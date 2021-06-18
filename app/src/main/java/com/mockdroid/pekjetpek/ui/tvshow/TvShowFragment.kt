package com.mockdroid.pekjetpek.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mockdroid.pekjetpek.databinding.FragmentTvShowBinding
import com.mockdroid.pekjetpek.utils.DataDummy

class TvShowFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]
            val tvShow = viewModel.getTvShow()
            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.setTvShow(tvShow)

            with(fragmentTvShowBinding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}