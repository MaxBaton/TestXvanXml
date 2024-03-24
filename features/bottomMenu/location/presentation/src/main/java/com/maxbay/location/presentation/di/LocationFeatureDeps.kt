package com.maxbay.location.presentation.di

import com.maxbay.location.domain.repository.LocationRepository

interface LocationFeatureDeps {
    val locationRepository: LocationRepository
}