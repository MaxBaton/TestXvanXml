package com.maxbay.table.api

import androidx.fragment.app.Fragment
import com.maxbay.tab_api.TabFeatureApi

interface TableFeatureApi: TabFeatureApi {
    override fun fragment(): Fragment
    override fun tabIconId(): Int
}