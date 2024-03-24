package com.maxbay.location.presentation.mapper

import com.maxbay.location.domain.models.Photo
import com.maxbay.location.presentation.logic.ReadBitmapFromFile
import com.maxbay.location.presentation.models.setcionData.PhotoUi

class PhotoMapper(private val readBitmapFromFile: ReadBitmapFromFile) {
    fun photoToUi(photo: Photo, locationId: Int): PhotoUi {
        return PhotoUi(
            id = photo.id,
            bitmap = readBitmapFromFile.read(
                locationId = locationId,
                fileName = photo.uriStr
            )
        )
    }
}