package com.maxbay.location.data.mappers

import com.maxbay.location.data.storage.db.dto.AllSectionsDataDto
import com.maxbay.location.data.storage.db.entities.LocationEntity
import com.maxbay.location.data.storage.db.entities.PhotoEntity
import com.maxbay.location.data.storage.models.LocationModel
import com.maxbay.location.data.storage.models.PhotoModel
import com.maxbay.location.data.storage.models.SectionModel

internal fun List<AllSectionsDataDto>.allSectionDataToStorage(): List<SectionModel> {
    val sectionModels = mutableListOf<SectionModel>()

    val map = mutableMapOf<SectionModel, Map<LocationModel, Set<PhotoModel>>>()

    this.forEach { allData ->
        val sectionModel = SectionModel(
            id = allData.sectionId,
            name = allData.sectionName,
            locations = emptyList()
        )

        val locationModel = LocationModel(
            id = allData.locationId,
            name = allData.locationName,
            photos = emptyList()
        )

        val photoModel = PhotoModel(
            id = allData.photoId,
            uriStr = allData.photoUri
        )

        if (map.containsKey(sectionModel)) {
            val locationPhotos = map[sectionModel]?.toMutableMap()
            val location = locationPhotos?.keys?.firstOrNull { it == locationModel }
            location?.let { locationMap ->
                val photos = locationPhotos[locationMap]
                photos?.let { photoSet ->
                    val newPhotos = photoSet.toMutableSet()
                    newPhotos.add(photoModel)
                    map[sectionModel] = mapOf(locationMap to newPhotos)
                }
            }

        }else {
            map[sectionModel] = mapOf(locationModel to setOf(photoModel))
        }
    }

    map.entries.forEach {
        val locations = mutableListOf<LocationModel>()

        it.value.entries.forEach { locationPhotos ->
            val location = locationPhotos.key.copy(photos = locationPhotos.value.toList())
            locations.add(location)
        }

        val section = it.key.copy(locations = locations)

        sectionModels.add(section)
    }


    return sectionModels
}

private fun PhotoModel.toEntity(locationId: Int) = PhotoEntity(
    id = this.id,
    uri = this.uriStr ?: "",
    idLocation = locationId
)

internal fun List<PhotoModel>.photoModelsToEntity(locationId: Int) = this.map { it.toEntity(locationId = locationId) }

internal fun LocationModel.toEntity(sectionId: Int) = LocationEntity(
    id = this.id,
    name = this.name,
    idSection = sectionId
)