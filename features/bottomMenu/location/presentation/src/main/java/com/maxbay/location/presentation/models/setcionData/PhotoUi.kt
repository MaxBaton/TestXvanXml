package com.maxbay.location.presentation.models.setcionData

import android.graphics.Bitmap

data class PhotoUi(
    val id: Int,
    val bitmap: Bitmap?,
    val isInDeleteMode: Boolean = false
)
