package com.maxbay.settings.api

import androidx.fragment.app.Fragment
import com.maxbay.tab_api.TabFeatureApi

interface SettingsFeatureApi: TabFeatureApi {
    override fun fragment(): Fragment
    override fun tabIconId(): Int
}