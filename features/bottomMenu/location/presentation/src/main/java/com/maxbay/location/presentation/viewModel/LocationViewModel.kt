package com.maxbay.location.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxbay.location.domain.useCase.ObserveSectionsUseCase
import com.maxbay.location.domain.useCase.SavePhotosByLocationUseCase
import com.maxbay.location.domain.useCase.UpdateSectionNameUseCase
import com.maxbay.location.presentation.mapper.toUI
import com.maxbay.location.presentation.models.SectionUi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LocationViewModel(
    private val savePhotosByLocationUseCase: SavePhotosByLocationUseCase,
    private val observeSectionsUseCase: ObserveSectionsUseCase,
    private val updateSectionNameUseCase: UpdateSectionNameUseCase
): ViewModel() {
    private val _sections = MutableLiveData<List<SectionUi>>()
    val sections: LiveData<List<SectionUi>>
        get() = _sections

    init {
        observeSections()
    }

    fun openGallery() {

    }

    fun addPhotos(locationId: Int, photosUri: List<String>) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

        }

        viewModelScope.launch(exceptionHandler) {
            if (photosUri.isNotEmpty()) {
                savePhotosByLocationUseCase.execute(locationId = locationId, photosUriStr = photosUri)
            }
        }
    }

    private fun observeSections() {
        viewModelScope.launch {
            observeSectionsUseCase.execute().collect { sections ->
                _sections.postValue(sections.toUI())
            }
        }
    }

    fun onChangeSectionName(sectionId: Int, newName: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, _ -> }
        viewModelScope.launch(exceptionHandler) {
            updateSectionNameUseCase.execute(sectionId = sectionId, name = newName)
        }
    }
}