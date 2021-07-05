package com.mockdroid.pekjetpek.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mockdroid.pekjetpek.ui.favorite.FavoriteFragment
import com.mockdroid.pekjetpek.ui.movie.MovieFragment
import com.mockdroid.pekjetpek.ui.tvshow.TvShowFragment
import com.mockdroid.pekjetpek.utils.Const.Companion.TAB_TITLES

class SectionsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = TAB_TITLES.size

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MovieFragment()
        1 -> TvShowFragment()
        2 -> FavoriteFragment()
        else -> Fragment()
    }
}