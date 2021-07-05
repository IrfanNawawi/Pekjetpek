package com.mockdroid.pekjetpek.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.mockdroid.pekjetpek.databinding.ActivityHomeBinding
import com.mockdroid.pekjetpek.utils.Const.Companion.TAB_TITLES

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, lifecycle)
        activityHomeBinding.viewpager.adapter = sectionsPagerAdapter
        TabLayoutMediator(
            activityHomeBinding.tabs,
            activityHomeBinding.viewpager
        ) { tabs, position ->
            tabs.text = resources.getString(
                TAB_TITLES[position]
            )
        }.attach()

        supportActionBar?.elevation = 0f
    }
}