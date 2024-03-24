package com.maxbay.location.presentation.di

import com.maxbay.location.presentation.viewModel.LocationViewModelFactory
import dagger.Component

@LocationScope
@Component(
    modules = [ LocationModule::class ],
    dependencies = [ LocationFeatureDeps::class ]
)
interface LocationComponent {
    val locationViewModelFactory: LocationViewModelFactory

    @Component.Builder
    interface Builder {
        fun addDeps(deps: LocationFeatureDeps): Builder
        fun build(): LocationComponent
    }
}