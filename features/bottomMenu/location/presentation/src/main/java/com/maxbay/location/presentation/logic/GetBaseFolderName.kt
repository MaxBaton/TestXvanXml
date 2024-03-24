package com.maxbay.location.presentation.logic

object GetBaseFolderName {
    fun get(locationId: Int) = "photos_$locationId"
}