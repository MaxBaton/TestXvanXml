package com.maxbay.location.presentation.models.setcionData

data class LocationUi(
    val id: Int,
    val name: String,
    val photos: List<PhotoUi>,
    val isInDeleteMode: Boolean = false
)
