package com.maxbay.testxvan.di.modules

import android.content.Context
import com.maxbay.location.data.storage.api.LocationStorage
import com.maxbay.location.data.storage.db.api.LocationDbStorage
import com.maxbay.location.data.storage.db.dao.LocationDao
import com.maxbay.testxvanxml.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {
    @Provides
    @Singleton
    fun provideLocationStorage(locationDao: LocationDao): LocationStorage {
        return LocationDbStorage(locationDao = locationDao)
    }

    @Provides
    fun provideLocationDao(db: AppDatabase): LocationDao {
        return db.locationDao()
    }

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.create(context = context)
    }
}