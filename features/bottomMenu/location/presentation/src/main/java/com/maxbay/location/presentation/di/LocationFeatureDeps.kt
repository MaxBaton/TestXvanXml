package com.maxbay.location.presentation.di

import android.content.ContentResolver
import android.content.Context
import com.maxbay.location.domain.repository.LocationRepository
import com.maxbay.location.presentation.logic.ReadBitmapFromFile
import com.maxbay.location.presentation.models.bitmap.AbsolutelyFilePath

interface LocationFeatureDeps {
    val locationRepository: LocationRepository
    val contentResolver: ContentResolver
    val absolutelyFilePath: AbsolutelyFilePath
    val context: Context
}