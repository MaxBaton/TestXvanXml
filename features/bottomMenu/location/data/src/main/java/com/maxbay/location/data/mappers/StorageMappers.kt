package com.maxbay.location.data.mappers

import com.maxbay.location.data.storage.models.LocationModel
import com.maxbay.location.data.storage.models.PhotoModel
import com.maxbay.location.data.storage.models.SectionModel
import com.maxbay.location.domain.models.Location
import com.maxbay.location.domain.models.Photo
import com.maxbay.location.domain.models.Section

private fun PhotoModel.toDomain() = Photo(
    id = this.id,
    uriStr = this.uriStr ?: ""
)

private fun List<PhotoModel>.photosToDomain() = this.map { it.toDomain() }

private fun LocationModel.toDomain() = Location(
    id = this.id,
    name = this.name,
    photos = this.photos.photosToDomain()
)

private fun List<LocationModel>.locationsToDomain() = this.map { it.toDomain() }

private fun SectionModel.toDomain() = Section(
    id = this.id,
    name = this.name,
    locations = this.locations.locationsToDomain()
)

internal fun List<SectionModel>.toDomain() = this.map { it.toDomain() }