package com.maxbay.location.data.storage.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = LocationEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = SectionEntity::class,
            parentColumns = [SectionEntity.ID],
            childColumns = [LocationEntity.ID_SECTION],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Int = 0,
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = ID_SECTION)
    val idSection: Int
) {
    companion object {
        const val TABLE_NAME = "Location"
        const val ID = "id"
        const val NAME = "name"
        const val ID_SECTION = "id_section"
    }
}
