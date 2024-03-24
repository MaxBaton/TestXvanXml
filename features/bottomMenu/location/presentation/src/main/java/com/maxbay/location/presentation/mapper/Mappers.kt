package com.maxbay.location.presentation.mapper

import com.maxbay.location.domain.models.Location
import com.maxbay.location.domain.models.Photo
import com.maxbay.location.domain.models.Section
import com.maxbay.location.presentation.models.setcionData.LocationUi
import com.maxbay.location.presentation.models.setcionData.PhotoUi
import com.maxbay.location.presentation.models.setcionData.SectionUi
import kotlinx.collections.immutable.toImmutableList

private fun Photo.toUi() = PhotoUi(
    id = this.id,
    uri = this.uriStr
)

private fun List<Photo>.photosToUi() = this.map { it.toUi() }

private fun Location.toUi() = LocationUi(
    id = this.id,
    name = this.name,
    photos = this.photos.photosToUi().toImmutableList()
)

private fun List<Location>.locationsToUi() = this.map { it.toUi() }

private fun Section.toUi() = SectionUi(
    id = this.id,
    name = this.name,
    locations = this.locations.locationsToUi()
)

internal fun List<Section>.toUI() = this.map { it.toUi() }