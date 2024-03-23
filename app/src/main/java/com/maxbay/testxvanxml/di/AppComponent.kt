package com.maxbay.testviewpager.di

import android.content.Context
import com.maxbay.testviewpager.navigation.NavigatorLifecycle
import com.maxbay.testxvan.navigation.Navigator
import com.maxbay.testxvanxml.di.modules.NavigationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NavigationModule::class
    ]
)
interface AppComponent {
    val navigatorLifecycle: NavigatorLifecycle
    val navigator: Navigator

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

}