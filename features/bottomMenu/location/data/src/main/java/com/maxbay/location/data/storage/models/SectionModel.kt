package com.maxbay.location.data.storage.models

data class SectionModel(
    val id: Int,
    val name: String,
    val locations: List<LocationModel>
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other::class != this::class) return false

        val newSection = other as SectionModel

        return this.id == newSection.id
                && this.name == newSection.name
                && isEqualsLocations(
                    oldLocations = this.locations,
                    newLocations = newSection.locations
                )
    }

    private fun isEqualsLocations(
        oldLocations: List<LocationModel>,
        newLocations: List<LocationModel>
    ): Boolean {
        if (oldLocations.size != newLocations.size) return false

        newLocations.forEachIndexed { index, locationNew ->
            val locationOld = oldLocations[index]

            if (
                locationNew.id != locationOld.id ||
                locationNew.name != locationOld.name ||
                !isEqualsPhotos(oldPhotos = locationOld.photos, newPhotos = locationNew.photos)
                ) return false
        }

        return true
    }

    private fun isEqualsPhotos(
        oldPhotos: List<PhotoModel>,
        newPhotos: List<PhotoModel>
    ): Boolean {
        if (oldPhotos.size != newPhotos.size) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + locations.hashCode()
        return result
    }
}
