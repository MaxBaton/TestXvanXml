package com.maxbay.testxvan.navigation

import androidx.fragment.app.Fragment

interface Navigator {
    fun back()
    fun open(fragment: Fragment)
    fun openWithBackStack(fragment: Fragment)
}