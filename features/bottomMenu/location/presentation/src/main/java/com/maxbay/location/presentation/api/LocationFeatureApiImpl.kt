package com.maxbay.location.presentation.api

import androidx.fragment.app.Fragment
import com.maxbay.location.api.LocationFeatureApi
import com.maxbay.location.presentation.R
import com.maxbay.location.presentation.ui.fragment.LocationFragment

class LocationFeatureApiImpl: LocationFeatureApi {
    override fun fragment(): Fragment = LocationFragment.newInstance()

    override fun tabIconId(): Int = R.drawable.ic_location_test
}