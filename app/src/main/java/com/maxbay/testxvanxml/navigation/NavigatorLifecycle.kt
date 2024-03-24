package com.maxbay.testviewpager.navigation

interface NavigatorLifecycle {
    fun onCreate(navigatorHolder: NavigatorHolder)
    fun onDestroy()
}