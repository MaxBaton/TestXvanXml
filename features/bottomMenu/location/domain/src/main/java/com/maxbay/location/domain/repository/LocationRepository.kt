package com.maxbay.location.domain.repository

import com.maxbay.location.domain.models.Section
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun savePhotosByLocation(locationId: Int, photosUriStr: List<String>)
    suspend fun observeSections(): Flow<List<Section>>
    suspend fun updateSectionName(sectionId: Int, newName: String)
    suspend fun deletePhotos(photosId: List<Int>)
}