package com.maxbay.location.domain.models

data class Location(
    val id: Int,
    val name: String,
    val photos: List<Photo>
)
