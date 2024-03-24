package com.maxbay.bottommenu.host.presentation.api

import androidx.fragment.app.Fragment
import com.maxbay.bottommenu.host.api.BottomMenuHostFeatureApi
import com.maxbay.presentation.ui.BottomMenuHostFragment

class BottomMenuHostFeatureApiImpl: BottomMenuHostFeatureApi {
    override fun open(): Fragment = BottomMenuHostFragment.newInstance()
}