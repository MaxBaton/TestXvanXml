package com.maxbay.location.data.storage.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = PhotoEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = LocationEntity::class,
            parentColumns = [ LocationEntity.ID ],
            childColumns = [ PhotoEntity.ID_LOCATION ],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Int = 0,
    @ColumnInfo(name = URI)
    val uri: String,
    @ColumnInfo(name = ID_LOCATION)
    val idLocation: Int
) {
    companion object {
        const val TABLE_NAME = "Photo"
        const val ID = "id"
        const val URI = "uri"
        const val ID_LOCATION = "id_location"
    }
}
