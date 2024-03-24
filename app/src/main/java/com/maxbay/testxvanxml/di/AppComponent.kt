package com.maxbay.testviewpager.di

import android.content.Context
import com.maxbay.bottommenu.host.api.BottomMenuHostFeatureApi
import com.maxbay.bottommenu.host.presentation.models.TabFeaturesApi
import com.maxbay.location.domain.repository.LocationRepository
import com.maxbay.location.presentation.di.LocationFeatureDeps
import com.maxbay.presentation.di.BottomMenuHostFeatureDeps
import com.maxbay.testviewpager.navigation.NavigatorLifecycle
import com.maxbay.testxvan.di.modules.RepositoryModule
import com.maxbay.testxvan.di.modules.StorageModule
import com.maxbay.testxvan.navigation.Navigator
import com.maxbay.testxvanxml.di.modules.FeaturesModule
import com.maxbay.testxvanxml.di.modules.NavigationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NavigationModule::class,
        FeaturesModule::class,
        StorageModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent: BottomMenuHostFeatureDeps, LocationFeatureDeps {
    val navigatorLifecycle: NavigatorLifecycle
    val navigator: Navigator
    val bottomMenuHostFeatureApi: BottomMenuHostFeatureApi

    override val tabFeaturesApi: TabFeaturesApi
    override val locationRepository: LocationRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

}