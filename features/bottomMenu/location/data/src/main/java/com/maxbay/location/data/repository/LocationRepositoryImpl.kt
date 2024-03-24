package com.maxbay.location.data.repository

import com.maxbay.location.data.mappers.toDomain
import com.maxbay.location.data.storage.api.LocationStorage
import com.maxbay.location.data.storage.models.PhotoModelAdd
import com.maxbay.location.domain.models.Section
import com.maxbay.location.domain.repository.LocationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


class LocationRepositoryImpl(
    private val locationStorage: LocationStorage,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): LocationRepository {
    override suspend fun savePhotosByLocation(locationId: Int, photosUriStr: List<String>) {
        withContext(dispatcher) {
            locationStorage.savePhotosByLocation(
                photos = photosUriStr.map {
                    PhotoModelAdd(uriStr = it, idLocation = locationId)
                }
            )
        }
    }

    override suspend fun observeSections(): Flow<List<Section>> {
        return withContext(dispatcher) {
            locationStorage.getAllSections().flowOn(dispatcher).map { it.toDomain() }
        }
    }

    override suspend fun updateSectionName(sectionId: Int, newName: String) {
        withContext(dispatcher) {
            locationStorage.updatSectionNameById(sectionId = sectionId, name = newName)
        }
    }
}