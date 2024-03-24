package com.maxbay.location.presentation.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxbay.location.domain.useCase.DeletePhotosUseCase
import com.maxbay.location.domain.useCase.GetPhotoFileNameUseCase
import com.maxbay.location.domain.useCase.ObserveSectionsUseCase
import com.maxbay.location.domain.useCase.SavePhotosByLocationUseCase
import com.maxbay.location.domain.useCase.UpdateSectionNameUseCase
import com.maxbay.location.presentation.logic.WriteBitmapToFile
import com.maxbay.location.presentation.mapper.UiMapper
import com.maxbay.location.presentation.models.screens.Screen
import com.maxbay.location.presentation.models.setcionData.PhotoDeleteMode
import com.maxbay.location.presentation.models.setcionData.SectionUi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LocationViewModel(
    private val savePhotosByLocationUseCase: SavePhotosByLocationUseCase,
    private val observeSectionsUseCase: ObserveSectionsUseCase,
    private val updateSectionNameUseCase: UpdateSectionNameUseCase,
    private val writeBitmapToFile: WriteBitmapToFile,
    private val getPhotoFileNameUseCase: GetPhotoFileNameUseCase,
    private val uiMapper: UiMapper,
    private val deletePhotosUseCase: DeletePhotosUseCase
): ViewModel() {
    private val _sections = MutableLiveData<List<SectionUi>>()
    val sections: LiveData<List<SectionUi>>
        get() = _sections

    private val _screen = MutableLiveData<Screen>()
    val screen: LiveData<Screen>
        get() = _screen

    private val _photosInDeleteMode = MutableLiveData<MutableList<Int>>(mutableListOf())

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

    fun changePhotoDeleteModeByClick(photoId: Int, isInDeleteMode: Boolean) {
        if (_photosInDeleteMode.value?.isNotEmpty() == true) {
            changePhotoDeleteMode(photoId, isInDeleteMode)
        }
    }

    fun changePhotoDeleteMode(photoId: Int, isInDeleteMode: Boolean) {
        _sections.value?.let { sections ->
            _sections.postValue(getNewSectionAfterChangeDeleteMode(
                sections = sections,
                photoId = photoId,
                isInDeleteMode = isInDeleteMode,
                isLastPhotoInDeleteMode = _photosInDeleteMode.value?.size == 1
            ))

            if (isInDeleteMode) {
                _photosInDeleteMode.value?.remove(photoId)
            }else{
                _photosInDeleteMode.value?.add(photoId)
            }
        }
    }

    private fun getNewSectionAfterChangeDeleteMode(
        sections: List<SectionUi>,
        photoId: Int,
        isInDeleteMode: Boolean,
        isLastPhotoInDeleteMode: Boolean
    ): List<SectionUi> {
        return sections.map { section ->
            val newLocations = section.locations.map { location ->
                val newPhotos = location.photos.map { photo ->
                    if (photo.id == photoId) {
                        if (!isInDeleteMode) {
                            photo.copy(deleteMode = PhotoDeleteMode.IN_DELETE_MODE)
                        }else {
                            if (isLastPhotoInDeleteMode) {
                                photo.copy(deleteMode = PhotoDeleteMode.NONE)
                            }else {
                                photo.copy(deleteMode = PhotoDeleteMode.INCLUDE_DELETE_MODE)
                            }
                        }
                    }else {
                        if (isInDeleteMode && isLastPhotoInDeleteMode) {
                            photo.copy(deleteMode = PhotoDeleteMode.NONE)
                        }else {
                            if (photo.deleteMode == PhotoDeleteMode.IN_DELETE_MODE) {
                                photo
                            }else {
                                photo.copy(deleteMode = PhotoDeleteMode.INCLUDE_DELETE_MODE)
                            }
                        }
                    }
                }

                location.copy(
                    photos = newPhotos,
                    isInDeleteMode = !(isInDeleteMode && isLastPhotoInDeleteMode)
                )
            }

            section.copy(locations = newLocations)
        }
    }

    fun confirmDeletePhotos() {
        _screen.postValue(Screen.ConfirmDeletePhotosDialogScreen)
    }

    fun deleteSelectedPhotos() {
        val exceptionHandler = CoroutineExceptionHandler { _, _ ->

        }

        viewModelScope.launch(exceptionHandler) {
            deletePhotosUseCase.execute(photosId = _photosInDeleteMode.value!!)
            _photosInDeleteMode.postValue(mutableListOf())
        }
    }
}