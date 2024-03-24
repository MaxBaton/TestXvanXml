package com.maxbay.presentation.di

import com.maxbay.bottommenu.host.presentation.models.TabFeaturesApi
import dagger.Component

@Component(
    dependencies = [BottomMenuHostFeatureDeps::class]
)
interface BottomMenuHostComponent {
    val tabFeaturesApi: TabFeaturesApi

    @Component.Builder
    interface Builder {
        fun addDeps(deps: BottomMenuHostFeatureDeps): Builder
        fun build(): BottomMenuHostComponent
    }
}