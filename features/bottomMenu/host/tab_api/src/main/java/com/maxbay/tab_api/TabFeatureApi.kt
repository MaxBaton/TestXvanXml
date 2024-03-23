package com.maxbay.tab_api

import androidx.fragment.app.Fragment

interface TabFeatureApi {
    fun fragment(): Fragment
    fun tabIconId(): Int
}