package com.maxbay.location.data.storage.db.dto

import androidx.room.ColumnInfo

data class AllSectionsDataDto(
    @ColumnInfo(name = SECTION_ID)
    val sectionId: Int,
    @ColumnInfo(name = SECTION_NAME)
    val sectionName: String,
    @ColumnInfo(name = LOCATION_ID)
    val locationId: Int,
    @ColumnInfo(name = LOCATION_NAME)
    val locationName: String,
    @ColumnInfo(name = LOCATION_SECTION_ID)
    val locationSectionId: Int,
    @ColumnInfo(name = PHOTO_ID)
    val photoId: Int,
    @ColumnInfo(name = PHOTO_URI)
    val photoUri: String?,
    @ColumnInfo(name = PHOTO_LOCATION_ID)
    val photoLocationId: Int
) {
    companion object {
        const val SECTION_ID = "section_id_dto"
        const val SECTION_NAME = "section_name_dto"
        const val LOCATION_ID = "location_id_dto"
        const val LOCATION_NAME = "location_name_dto"
        const val LOCATION_SECTION_ID = "location_section_id_dto"
        const val PHOTO_ID = "photo_id_dto"
        const val PHOTO_URI = "photo_uri_dto"
        const val PHOTO_LOCATION_ID = "photo_location_id_dto"
    }
}