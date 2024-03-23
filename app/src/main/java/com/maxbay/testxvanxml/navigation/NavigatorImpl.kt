package com.maxbay.testviewpager.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.maxbay.testxvan.navigation.Navigator
import com.maxbay.testxvanxml.R

class NavigatorImpl: Navigator, NavigatorLifecycle {
    private var navigatorHolder: NavigatorHolder? = null

    override fun onCreate(navigatorHolder: NavigatorHolder) {
        this.navigatorHolder = navigatorHolder
    }

    override fun onDestroy() {
        navigatorHolder = null
    }

    override fun open(fragment: Fragment) {
        navigatorHolder?.manager()?.commit {
            replace(R.id.main_nav_host, fragment)
        }
    }

    override fun openWithBackStack(fragment: Fragment) {
        navigatorHolder?.manager()?.commit {
            replace(R.id.main_nav_host, fragment)
            addToBackStack(null)
        }
    }

    override fun back() {
        navigatorHolder?.manager()?.popBackStack()
    }
}