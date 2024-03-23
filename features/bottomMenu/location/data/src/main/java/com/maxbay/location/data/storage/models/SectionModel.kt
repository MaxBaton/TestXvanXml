package com.maxbay.location.data.storage.models

data class SectionModel(
    val id: Int,
    val name: String,
    val locations: List<LocationModel>
)
