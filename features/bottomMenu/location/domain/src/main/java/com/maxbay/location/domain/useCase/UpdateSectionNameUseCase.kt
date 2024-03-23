package com.maxbay.location.domain.useCase

import com.maxbay.location.domain.repository.LocationRepository

class UpdateSectionNameUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(sectionId: Int, name: String) {
        locationRepository.updateSectionName(sectionId = sectionId, newName = name)
    }
}