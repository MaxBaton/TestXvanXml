package com.maxbay.testxvan.di.modules

import com.maxbay.location.data.repository.LocationRepositoryImpl
import com.maxbay.location.data.storage.api.LocationStorage
import com.maxbay.location.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideLocationRepository(
        locationStorage: LocationStorage
    ): LocationRepository {
        return LocationRepositoryImpl(
            locationStorage = locationStorage
        )
    }
}