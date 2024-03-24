package com.maxbay.location.presentation.mapper

import com.maxbay.location.domain.models.Location
import com.maxbay.location.domain.models.Photo
import com.maxbay.location.domain.models.Section
import com.maxbay.location.presentation.models.setcionData.LocationUi
import com.maxbay.location.presentation.models.setcionData.SectionUi
import kotlinx.collections.immutable.toImmutableList

class UiMapper(private val photoMapper: PhotoMapper) {
    private fun photosToUi(photos: List<Photo>, locationId: Int) = photos.map {
        photoMapper.photoToUi(photo = it, locationId = locationId)
    }

    private fun locationToUi(location: Location) = LocationUi(
        id = location.id,
        name = location.name,
        photos = photosToUi(photos = location.photos, locationId = location.id).toImmutableList()
    )

    private fun locationsToUi(locations: List<Location>) = locations.map {
        locationToUi(location = it)
    }

    private fun sectionToUi(section: Section) = SectionUi(
        id = section.id,
        name = section.name,
        locations = locationsToUi(locations = section.locations)
    )

    internal fun sectionsToUI(sections: List<Section>) = sections.map {
        sectionToUi(section = it)
    }
}