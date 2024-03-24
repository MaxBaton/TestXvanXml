package com.maxbay.location.presentation.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxbay.location.domain.useCase.GetPhotoFileNameUseCase
import com.maxbay.location.domain.useCase.ObserveSectionsUseCase
import com.maxbay.location.domain.useCase.SavePhotosByLocationUseCase
import com.maxbay.location.domain.useCase.UpdateSectionNameUseCase
import com.maxbay.location.presentation.logic.WriteBitmapToFile
import com.maxbay.location.presentation.mapper.UiMapper
import com.maxbay.location.presentation.models.screens.Screen
import com.maxbay.location.presentation.models.setcionData.SectionUi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LocationViewModel(
    private val savePhotosByLocationUseCase: SavePhotosByLocationUseCase,
    private val observeSectionsUseCase: ObserveSectionsUseCase,
    private val updateSectionNameUseCase: UpdateSectionNameUseCase,
    private val writeBitmapToFile: WriteBitmapToFile,
    private val getPhotoFileNameUseCase: GetPhotoFileNameUseCase,
    private val uiMapper: UiMapper
): ViewModel() {
    private val _sections = MutableLiveData<List<SectionUi>>()
    val sections: LiveData<List<SectionUi>>
        get() = _sections

    private val _screen = MutableLiveData<Screen>()
    val screen: LiveData<Screen>
        get() = _screen

    init {
        observeSections()
    }

    fun openGallery(locationId: Int) {
        _screen.postValue(Screen.GalleryScreen(locationId = locationId))
    }

    fun savePhotos(locationId: Int, photosUri: List<Uri>) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            val l = throwable
        }

        viewModelScope.launch(exceptionHandler) {
            val photoNames = mutableListOf<String>()

            for (i in 1..photosUri.size) {
                val photoUri = photosUri[i - 1]
                val fileName = getPhotoFileNameUseCase.execute(additionalSecond = i)
                writeBitmapToFile.write(
                    fileName = fileName,
                    locationId = locationId,
                    uri = photoUri
                )

                photoNames.add(fileName)
            }

            savePhotosByLocationUseCase.execute(
                locationId = locationId,
                photosUriStr = photoNames
            )
        }
    }

    private fun observeSections() {
        viewModelScope.launch {
            observeSectionsUseCase.execute().collect { sections ->
                _sections.postValue(uiMapper.sectionsToUI(sections = sections))
            }
        }
    }

    fun onChangeSectionName(sectionId: Int, newName: String) {
        val exceptionHandler = CoroutineExceptionHandler { _, _ -> }
        viewModelScope.launch(exceptionHandler) {
            updateSectionNameUseCase.execute(sectionId = sectionId, name = newName)
        }
    }

    fun clearState() {
        _screen.postValue(Screen.None)
    }
}