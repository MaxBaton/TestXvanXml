package com.maxbay.money.api

import androidx.fragment.app.Fragment
import com.maxbay.tab_api.TabFeatureApi

interface MoneyFeatureApi: TabFeatureApi {
    override fun fragment(): Fragment
    override fun tabIconId(): Int
}