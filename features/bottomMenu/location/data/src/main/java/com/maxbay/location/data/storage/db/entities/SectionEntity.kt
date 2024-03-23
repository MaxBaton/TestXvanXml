package com.maxbay.location.data.storage.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = SectionEntity.TABLE_NAME)
data class SectionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Int = 0,
    @ColumnInfo(name = NAME)
    val name: String
) {
    companion object {
        const val TABLE_NAME = "Section"
        const val ID = "id"
        const val NAME = "name"
    }
}
