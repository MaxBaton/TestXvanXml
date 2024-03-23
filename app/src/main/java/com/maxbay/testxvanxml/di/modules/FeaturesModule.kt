package com.maxbay.testxvanxml.di.modules

import com.maxbay.bottommenu.host.api.BottomMenuHostFeatureApi
import com.maxbay.bottommenu.host.presentation.api.BottomMenuHostFeatureApiImpl
import com.maxbay.bottommenu.host.presentation.models.TabFeaturesApi
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

    ): TabFeaturesApi {
        return TabFeaturesApi(featuresApi = listOf())
    }
}