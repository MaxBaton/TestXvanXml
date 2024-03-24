package com.maxbay.location.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maxbay.location.domain.useCase.GetPhotoFileNameUseCase
import com.maxbay.location.domain.useCase.ObserveSectionsUseCase
import com.maxbay.location.domain.useCase.SavePhotosByLocationUseCase
import com.maxbay.location.domain.useCase.UpdateSectionNameUseCase
import com.maxbay.location.presentation.logic.ReadBitmapFromFile
import com.maxbay.location.presentation.logic.WriteBitmapToFile
import com.maxbay.location.presentation.mapper.UiMapper

@Suppress("UNCHECKED_CAST")
class LocationViewModelFactory(
    private val savePhotosByLocationUseCase: SavePhotosByLocationUseCase,
    private val observeSectionsUseCase: ObserveSectionsUseCase,
    private val updateSectionNameUseCase: UpdateSectionNameUseCase,
    private val writeBitmapToFile: WriteBitmapToFile,
    private val getPhotoFileNameUseCase: GetPhotoFileNameUseCase,
    private val uiMapper: UiMapper
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationViewModel(
            savePhotosByLocationUseCase = savePhotosByLocationUseCase,
            observeSectionsUseCase = observeSectionsUseCase,
            updateSectionNameUseCase = updateSectionNameUseCase,
            writeBitmapToFile = writeBitmapToFile,
            getPhotoFileNameUseCase = getPhotoFileNameUseCase,
            uiMapper = uiMapper
        ) as T
    }
}