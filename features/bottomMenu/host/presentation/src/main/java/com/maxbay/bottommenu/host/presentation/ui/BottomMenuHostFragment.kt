package com.maxbay.presentation.ui

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.maxbay.bottommenu.host.presentation.R
import com.maxbay.bottommenu.host.presentation.databinding.FragmentHostBinding
import com.maxbay.presentation.di.BottomMenuHostFeatureDepsProvider
import com.maxbay.presentation.di.DaggerBottomMenuHostComponent


class BottomMenuHostFragment: Fragment(R.layout.fragment_host) {
    private var binding: FragmentHostBinding? = null
    private val authHostComponent by lazy {
        DaggerBottomMenuHostComponent
            .builder()
            .addDeps(deps = BottomMenuHostFeatureDepsProvider.deps)
            .build()
    }
    private val tabFeaturesApi by lazy {
        authHostComponent.tabFeaturesApi
    }

    companion object {
        fun newInstance(): BottomMenuHostFragment {
            return BottomMenuHostFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHostBinding.bind(view)

        with(binding ?: return) {
            viewPager.adapter = PagerAdapter(
                fragmentActivity = requireActivity(),
                tabsFeaturesApi = tabFeaturesApi.featuresApi
            )

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.setIcon(tabFeaturesApi.featuresApi[position].tabIconId())
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun getColorByAttr(attrColor: Int): Int {
        val typedValue = TypedValue()
        val theme = requireContext().theme
        theme.resolveAttribute(attrColor, typedValue, true)
        return typedValue.data
    }
}