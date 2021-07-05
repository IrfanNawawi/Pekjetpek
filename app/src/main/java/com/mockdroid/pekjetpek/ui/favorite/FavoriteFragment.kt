package com.mockdroid.pekjetpek.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.mockdroid.pekjetpek.R
import com.mockdroid.pekjetpek.databinding.FragmentFavoriteBinding
import com.mockdroid.pekjetpek.ui.favorite.movie_fav.MovieFavoriteFragment
import com.mockdroid.pekjetpek.ui.favorite.tvshow_fav.TvShowFavoriteFragment

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            setViewPager()
        }
    }

    private fun setViewPager() {
        val fragmentList = listOf(MovieFavoriteFragment(), TvShowFavoriteFragment())
        val tabTitle =
            listOf(
                resources.getString(R.string.movie_fav),
                resources.getString(R.string.tv_show_fav)
            )

        binding?.viewpager?.adapter =
            ViewpagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        TabLayoutMediator(binding!!.tablayout, binding!!.viewpager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}