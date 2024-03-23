package com.maxbay.settings.presentation.api

import androidx.fragment.app.Fragment
import com.maxbay.settings.api.SettingsFeatureApi
import com.maxbay.settings.presentation.R
import com.maxbay.settings.presentation.ui.SettingsFragment

class SettingsFeatureApiImpl: SettingsFeatureApi {
    override fun fragment(): Fragment = SettingsFragment.newInstance()

    override fun tabIconId(): Int = R.drawable.ic_settings
}