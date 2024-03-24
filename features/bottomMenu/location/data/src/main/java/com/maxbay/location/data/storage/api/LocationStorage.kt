package com.maxbay.location.data.storage.api

import com.maxbay.location.data.storage.models.PhotoModelAdd
import com.maxbay.location.data.storage.models.SectionModel
import kotlinx.coroutines.flow.Flow

interface LocationStorage {
    suspend fun getAllSections(): Flow<List<SectionModel>>
    suspend fun savePhotosByLocation(photos: List<PhotoModelAdd>)
    suspend fun updatSectionNameById(sectionId: Int, name: String)
    suspend fun deletePhotos(photosId: List<Int>)
}