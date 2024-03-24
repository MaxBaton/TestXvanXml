package com.maxbay.location.domain.models

data class Section(
    val id: Int,
    val name: String,
    val locations: List<Location>
)
