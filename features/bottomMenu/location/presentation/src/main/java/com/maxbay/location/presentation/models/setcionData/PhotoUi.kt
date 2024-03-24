package com.maxbay.location.presentation.models.setcionData

import android.graphics.Bitmap

data class PhotoUi(
    val id: Int,
    val bitmap: Bitmap?,
    val deleteMode: PhotoDeleteMode = PhotoDeleteMode.NONE
)

enum class PhotoDeleteMode {
    NONE,
    INCLUDE_DELETE_MODE,
    IN_DELETE_MODE
}