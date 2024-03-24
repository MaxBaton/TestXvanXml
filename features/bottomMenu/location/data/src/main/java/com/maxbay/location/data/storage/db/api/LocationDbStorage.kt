package com.maxbay.location.data.storage.db.api

import com.maxbay.location.data.mappers.allSectionDataToStorage
import com.maxbay.location.data.storage.api.LocationStorage
import com.maxbay.location.data.storage.db.dao.LocationDao
import com.maxbay.location.data.storage.db.entities.PhotoEntity
import com.maxbay.location.data.storage.models.LocationModel
import com.maxbay.location.data.storage.models.PhotoModelAdd
import com.maxbay.location.data.storage.models.SectionModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

class LocationDbStorage(
    private val locationDao: LocationDao
): LocationStorage {
    private val _sectionsFlow = MutableStateFlow<List<SectionModel>>(emptyList())

    override suspend fun getAllSections(): Flow<List<SectionModel>> {
        _sectionsFlow.update {
            getSectionsFromDb()
        }
        return _sectionsFlow
    }

    private suspend fun getSectionsFromDb(): List<SectionModel> {
        val sections = locationDao.getSections()
        return if (sections.isEmpty()) {
            val initialList = getInitialSectionsData()
            initialList.forEach { sectionModel ->
                locationDao.addSectionData(sectionModel = sectionModel)
            }
            initialList
        }else {
            sections.allSectionDataToStorage()
        }
    }

    private fun getInitialSectionsData(): List<SectionModel> {
        return listOf(
            SectionModel(
                id = 1,
                name = "",
                locations = listOf(
                    LocationModel(
                        id = 1,
                        name = "",
                        photos = emptyList()
                    )
                )
            )
        )
    }

    override suspend fun savePhotosByLocation(photos: List<PhotoModelAdd>) {
        locationDao.addPhotos(
            photos = photos.map {
                PhotoEntity(uri = it.uriStr, idLocation = it.idLocation)
            }
        )
        _sectionsFlow.update {
            getSectionsFromDb()
        }
    }

    override suspend fun updatSectionNameById(sectionId: Int, name: String) {
        locationDao.updateSectionNameById(id = sectionId, name = name)
    }

    override suspend fun deletePhotos(photosId: List<Int>) {
        locationDao.deletePhotosByIds(ids = photosId)
        _sectionsFlow.update {
            getSectionsFromDb()
        }
    }
}