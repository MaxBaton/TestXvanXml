package com.maxbay.location.presentation.di

import com.maxbay.location.domain.repository.LocationRepository
import com.maxbay.location.domain.useCase.ObserveSectionsUseCase
import com.maxbay.location.domain.useCase.SavePhotosByLocationUseCase
import com.maxbay.location.domain.useCase.UpdateSectionNameUseCase
import com.maxbay.location.presentation.viewModel.LocationViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class LocationModule {
    @Provides
    fun provideLocationViewModelFactory(
        savePhotosByLocationUseCase: SavePhotosByLocationUseCase,
        observeSectionsUseCase: ObserveSectionsUseCase,
        updateSectionNameUseCase: UpdateSectionNameUseCase
    ): LocationViewModelFactory {
        return LocationViewModelFactory(
            savePhotosByLocationUseCase = savePhotosByLocationUseCase,
            observeSectionsUseCase = observeSectionsUseCase,
            updateSectionNameUseCase = updateSectionNameUseCase
        )
    }

    @Provides
    fun provideSavePhotosByLocationUseCase(locationRepository: LocationRepository): SavePhotosByLocationUseCase {
        return SavePhotosByLocationUseCase(locationRepository = locationRepository)
    }

    @Provides
    fun provideObserveSectionsUseCase(locationRepository: LocationRepository): ObserveSectionsUseCase {
        return ObserveSectionsUseCase(locationRepository = locationRepository)
    }

    @Provides
    fun provideUpdateSectionNameUseCase(locationRepository: LocationRepository): UpdateSectionNameUseCase {
        return UpdateSectionNameUseCase(locationRepository = locationRepository)
    }
}