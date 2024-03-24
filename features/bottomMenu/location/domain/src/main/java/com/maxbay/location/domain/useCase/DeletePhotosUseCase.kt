package com.maxbay.location.domain.useCase

import com.maxbay.location.domain.repository.LocationRepository

class DeletePhotosUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(photosId: List<Int>) {
        locationRepository.deletePhotos(photosId = photosId)
    }
}