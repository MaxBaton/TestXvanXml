package com.maxbay.testxvanxml.di.modules

import com.maxbay.testviewpager.navigation.NavigatorImpl
import com.maxbay.testviewpager.navigation.NavigatorLifecycle
import com.maxbay.testxvan.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {
    @Singleton
    @Provides
    fun provideNavigatorImpl(): NavigatorImpl {
        return NavigatorImpl()
    }

    @Provides
    fun provideNavigator(navigatorImpl: NavigatorImpl): Navigator {
        return navigatorImpl
    }

    @Provides
    fun provideNavigatorLifecycle(navigatorImpl: NavigatorImpl): NavigatorLifecycle {
        return navigatorImpl
    }
}