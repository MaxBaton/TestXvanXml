package com.maxbay.location.data.storage.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.maxbay.location.data.mappers.photoModelsToEntity
import com.maxbay.location.data.mappers.toEntity
import com.maxbay.location.data.storage.db.dto.AllSectionsDataDto
import com.maxbay.location.data.storage.db.entities.LocationEntity
import com.maxbay.location.data.storage.db.entities.PhotoEntity
import com.maxbay.location.data.storage.db.entities.SectionEntity
import com.maxbay.location.data.storage.models.SectionModel

@Dao
interface LocationDao {
    @Query(
        "select "
        + "s.${SectionEntity.ID} as ${AllSectionsDataDto.SECTION_ID}, "
        + "s.${SectionEntity.NAME} as ${AllSectionsDataDto.SECTION_NAME}, "
        + "l.${LocationEntity.ID} as ${AllSectionsDataDto.LOCATION_ID}, "
        + "l.${LocationEntity.NAME} as ${AllSectionsDataDto.LOCATION_NAME}, "
        + "l.${LocationEntity.ID_SECTION} as ${AllSectionsDataDto.LOCATION_SECTION_ID}, "
        + "p.${PhotoEntity.ID} as ${AllSectionsDataDto.PHOTO_ID}, "
        + "p.${PhotoEntity.URI} as ${AllSectionsDataDto.PHOTO_URI}, "
        + "p.${PhotoEntity.ID_LOCATION} as ${AllSectionsDataDto.PHOTO_LOCATION_ID} "
        + "from ${SectionEntity.TABLE_NAME} s inner join ${LocationEntity.TABLE_NAME} l "
        + "on s.${SectionEntity.ID} = l.${LocationEntity.ID_SECTION} "
        + "left join ${PhotoEntity.TABLE_NAME} p on l.${LocationEntity.ID} = p.${PhotoEntity.ID_LOCATION} "
        + "order by s.${SectionEntity.ID}, s.${SectionEntity.NAME}, l.${LocationEntity.NAME}"
    )
    suspend fun getSections(): List<AllSectionsDataDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSection(sectionEntity: SectionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLocation(location: LocationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPhotos(photos: List<PhotoEntity>)

    @Transaction
    fun addSectionData(sectionModel: SectionModel) {
        addSection(sectionEntity = SectionEntity(id = sectionModel.id, name = sectionModel.name))
        sectionModel.locations.forEach { locationModel ->
            addPhotos(photos = locationModel.photos.photoModelsToEntity(locationId = locationModel.id))
            addLocation(location = locationModel.toEntity(sectionId = sectionModel.id))
        }
    }

    @Query(
        "update ${SectionEntity.TABLE_NAME} set "
        + "${SectionEntity.NAME} = :name where ${SectionEntity.ID} = :id"
    )
    fun updateSectionNameById(id: Int, name: String)

    @Query("delete from ${PhotoEntity.TABLE_NAME} where ${PhotoEntity.ID} in (:ids)")
    fun deletePhotosByIds(ids: List<Int>)
}