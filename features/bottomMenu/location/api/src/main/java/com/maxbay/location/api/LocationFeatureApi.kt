package com.maxbay.location.api

import androidx.fragment.app.Fragment
import com.maxbay.tab_api.TabFeatureApi

interface LocationFeatureApi: TabFeatureApi {
    override fun fragment(): Fragment
    override fun tabIconId(): Int
}