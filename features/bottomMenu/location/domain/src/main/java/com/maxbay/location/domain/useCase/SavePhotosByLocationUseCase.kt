package com.maxbay.location.domain.useCase

import com.maxbay.location.domain.repository.LocationRepository

class SavePhotosByLocationUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(locationId: Int, photosUriStr: List<String>) {
        locationRepository.savePhotosByLocation(
            locationId = locationId,
            photosUriStr = photosUriStr
        )
    }
}