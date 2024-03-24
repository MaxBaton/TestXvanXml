package com.maxbay.testxvanxml.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.maxbay.testviewpager.di.DaggerAppProvider
import com.maxbay.testviewpager.navigation.NavigatorHolder
import com.maxbay.testviewpager.navigation.NavigatorLifecycle
import com.maxbay.testxvan.navigation.Navigator
import com.maxbay.testxvanxml.R

class NavigatorFragment: Fragment(R.layout.fragment_navigator), NavigatorHolder {
    private val navigatorLifecycle: NavigatorLifecycle by lazy {
        DaggerAppProvider.appComponent.navigatorLifecycle
    }
    private val navigator: Navigator by lazy {
        DaggerAppProvider.appComponent.navigator
    }
    private val bottomMenuHostFeatureApi by lazy {
        DaggerAppProvider.appComponent.bottomMenuHostFeatureApi
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigatorLifecycle.onCreate(navigatorHolder = this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            navigator.open(fragment = bottomMenuHostFeatureApi.open())
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            owner = viewLifecycleOwner,
            onBackPressedCallback = object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (childFragmentManager.backStackEntryCount > 0) {
                        childFragmentManager.popBackStack()
                    }else {
                        requireActivity().finish()
                    }
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        navigatorLifecycle.onDestroy()
    }

    override fun manager(): FragmentManager = childFragmentManager

    override fun context(): Context = requireContext()
}