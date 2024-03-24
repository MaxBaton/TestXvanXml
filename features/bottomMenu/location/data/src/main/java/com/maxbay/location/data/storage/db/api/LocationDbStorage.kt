package com.maxbay.location.data.storage.db.api

import com.maxbay.location.data.mappers.allSectionDataToStorage
import com.maxbay.location.data.storage.api.LocationStorage
import com.maxbay.location.data.storage.db.dao.LocationDao
import com.maxbay.location.data.storage.db.entities.PhotoEntity
import com.maxbay.location.data.storage.models.LocationModel
import com.maxbay.location.data.storage.models.PhotoModelAdd
import com.maxbay.location.data.storage.models.SectionModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.withContext

class LocationDbStorage(
    private val locationDao: LocationDao
): LocationStorage {
    override suspend fun getAllSections(): Flow<List<SectionModel>> {
        val sections = locationDao.getSections()
        val allData = if (sections.isEmpty()) {
            val initialList = getInitialSectionsData()
            initialList.forEach { sectionModel ->
                locationDao.addSectionData(sectionModel = sectionModel)
            }
            initialList
        }else {
            sections.allSectionDataToStorage()
        }

        return flow {
            emit(allData)
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
        getAllSections()
    }

    override suspend fun updatSectionNameById(sectionId: Int, name: String) {
        locationDao.updateSectionNameById(id = sectionId, name = name)
        getAllSections()
    }

    override suspend fun deletePhotos(photosId: List<Int>) {
        locationDao.deletePhotosByIds(ids = photosId)
        getAllSections()
    }
}