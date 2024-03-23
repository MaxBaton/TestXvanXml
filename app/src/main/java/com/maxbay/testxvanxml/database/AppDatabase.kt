package com.maxbay.testxvanxml.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maxbay.location.data.storage.db.dao.LocationDao
import com.maxbay.location.data.storage.db.entities.LocationEntity
import com.maxbay.location.data.storage.db.entities.PhotoEntity
import com.maxbay.location.data.storage.db.entities.SectionEntity

private const val DATABASE_VERSION = 1
private const val DATABASE_NAME = "location.db"
@Database(
    entities = [ SectionEntity::class, LocationEntity::class, PhotoEntity::class ],
    version = DATABASE_VERSION
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun locationDao(): LocationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        fun create(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(LOCK) {
                val db = Room.databaseBuilder(
                    context = context,
                    klass = AppDatabase::class.java,
                    name = DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = db

                db
            }
        }
    }
}