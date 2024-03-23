package com.maxbay.testxvanxml.di.modules

import com.maxbay.bottommenu.host.api.BottomMenuHostFeatureApi
import com.maxbay.bottommenu.host.presentation.api.BottomMenuHostFeatureApiImpl
import com.maxbay.bottommenu.host.presentation.models.TabFeaturesApi
import com.maxbay.location.api.LocationFeatureApi
import com.maxbay.location.presentation.api.LocationFeatureApiImpl
import com.maxbay.money.api.MoneyFeatureApi
import com.maxbay.money.presentation.api.MoneyFeatureApiImpl
import com.maxbay.settings.api.SettingsFeatureApi
import com.maxbay.settings.presentation.api.SettingsFeatureApiImpl
import com.maxbay.table.api.TableFeatureApi
import com.maxbay.table.presentation.api.TableFeatureApiImpl
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
        settingsFeatureApi: SettingsFeatureApi,
        moneyFeatureApi: MoneyFeatureApi,
        tableFeatureApi: TableFeatureApi,
        locationFeatureApi: LocationFeatureApi
    ): TabFeaturesApi {
        return TabFeaturesApi(
            featuresApi = listOf(
                settingsFeatureApi,
                moneyFeatureApi,
                tableFeatureApi,
                locationFeatureApi
            )
        )
    }

    @Provides
    fun provideSettingsFeatureApi(): SettingsFeatureApi {
        return SettingsFeatureApiImpl()
    }

    @Provides
    fun provideMoneyFeatureApi(): MoneyFeatureApi {
        return MoneyFeatureApiImpl()
    }

    @Provides
    fun provideTableFeatureApi(): TableFeatureApi {
        return TableFeatureApiImpl()
    }

    @Provides
    fun provideLocationFeatureApi(): LocationFeatureApi {
        return LocationFeatureApiImpl()
    }
}