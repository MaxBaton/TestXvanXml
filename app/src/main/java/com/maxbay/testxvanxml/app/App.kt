package com.maxbay.testxvanxml.app

import android.app.Application
import com.maxbay.presentation.di.BottomMenuHostFeatureDepsProvider
import com.maxbay.testviewpager.di.DaggerAppComponent
import com.maxbay.testviewpager.di.DaggerAppProvider

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private fun setupDagger() {
        val appComponent = DaggerAppComponent
            .builder()
            .context(context = this)
            .build()

        DaggerAppProvider.appComponent = appComponent
        BottomMenuHostFeatureDepsProvider.deps = appComponent
    }
}