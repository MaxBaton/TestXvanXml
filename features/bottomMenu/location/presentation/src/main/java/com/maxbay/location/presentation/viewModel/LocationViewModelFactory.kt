package com.maxbay.location.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maxbay.location.domain.useCase.ObserveSectionsUseCase
import com.maxbay.location.domain.useCase.SavePhotosByLocationUseCase
import com.maxbay.location.domain.useCase.UpdateSectionNameUseCase

@Suppress("UNCHECKED_CAST")
class LocationViewModelFactory(
    private val savePhotosByLocationUseCase: SavePhotosByLocationUseCase,
    private val observeSectionsUseCase: ObserveSectionsUseCase,
    private val updateSectionNameUseCase: UpdateSectionNameUseCase
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationViewModel(
            savePhotosByLocationUseCase = savePhotosByLocationUseCase,
            observeSectionsUseCase = observeSectionsUseCase,
            updateSectionNameUseCase = updateSectionNameUseCase
        ) as T
    }
}