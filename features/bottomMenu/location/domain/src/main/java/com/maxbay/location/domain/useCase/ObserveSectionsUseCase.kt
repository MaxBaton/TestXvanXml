package com.maxbay.location.domain.useCase

import com.maxbay.location.domain.models.Section
import com.maxbay.location.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class ObserveSectionsUseCase(private val locationRepository: LocationRepository) {
    suspend fun execute(): Flow<List<Section>> {
        return locationRepository.observeSections()
    }
}