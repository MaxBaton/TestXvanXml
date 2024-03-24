package com.maxbay.location.presentation.models.screens

sealed interface Screen {
    data object None: Screen
    data class GalleryScreen(val locationId: Int): Screen
}