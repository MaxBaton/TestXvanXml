package com.maxbay.testxvanxml.di.modules

import com.maxbay.bottommenu.host.api.BottomMenuHostFeatureApi
import com.maxbay.bottommenu.host.presentation.api.BottomMenuHostFeatureApiImpl
import com.maxbay.bottommenu.host.presentation.models.TabFeaturesApi
import com.maxbay.settings.api.SettingsFeatureApi
import com.maxbay.settings.presentation.api.SettingsFeatureApiImpl
import dagger.Module
import dagger.Provides

@Module
class FeaturesModule {
    @Provides
    fun provideAuthHostFeatureApi(): BottomMenuHostFeatureApi {
        return BottomMenuHostFeatureApiImpl()
    }

    @Provides
    fun provideTabFeaturesApi(
        settingsFeatureApi: SettingsFeatureApi
    ): TabFeaturesApi {
        return TabFeaturesApi(
            featuresApi = listOf(settingsFeatureApi)
        )
    }

    @Provides
    fun provideSettingsFeatureApi(): SettingsFeatureApi {
        return SettingsFeatureApiImpl()
    }
}