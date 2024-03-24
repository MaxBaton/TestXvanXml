package com.maxbay.presentation.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.maxbay.tab_api.TabFeatureApi

class PagerAdapter(
    fragmentActivity: FragmentActivity,
    private val tabsFeaturesApi: List<TabFeatureApi>
): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = tabsFeaturesApi.size
    override fun createFragment(position: Int): Fragment {
        return tabsFeaturesApi[position].fragment()
    }
}