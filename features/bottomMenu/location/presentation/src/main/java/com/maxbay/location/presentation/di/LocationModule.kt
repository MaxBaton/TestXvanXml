package com.maxbay.location.presentation.di

import android.content.ContentResolver
import com.maxbay.location.domain.repository.LocationRepository
import com.maxbay.location.domain.useCase.GetPhotoFileNameUseCase
import com.maxbay.location.domain.useCase.ObserveSectionsUseCase
import com.maxbay.location.domain.useCase.SavePhotosByLocationUseCase
import com.maxbay.location.domain.useCase.UpdateSectionNameUseCase
import com.maxbay.location.presentation.logic.GetBitmapFromUri
import com.maxbay.location.presentation.logic.WriteBitmapToFile
import com.maxbay.location.presentation.models.bitmap.AbsolutelyFilePath
import com.maxbay.location.presentation.viewModel.LocationViewModelFactory
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Module
class LocationModule {
    @Provides
    fun provideLocationViewModelFactory(
        savePhotosByLocationUseCase: SavePhotosByLocationUseCase,
        observeSectionsUseCase: ObserveSectionsUseCase,
        updateSectionNameUseCase: UpdateSectionNameUseCase,
        writeBitmapToFile: WriteBitmapToFile,
        getPhotoFileNameUseCase: GetPhotoFileNameUseCase
    ): LocationViewModelFactory {
        return LocationViewModelFactory(
            savePhotosByLocationUseCase = savePhotosByLocationUseCase,
            observeSectionsUseCase = observeSectionsUseCase,
            updateSectionNameUseCase = updateSectionNameUseCase,
            writeBitmapToFile = writeBitmapToFile,
            getPhotoFileNameUseCase = getPhotoFileNameUseCase
        )
    }

    @Provides
    fun provideSavePhotosByLocationUseCase(locationRepository: LocationRepository): SavePhotosByLocationUseCase {
        return SavePhotosByLocationUseCase(locationRepository = locationRepository)
    }

    @Provides
    fun provideObserveSectionsUseCase(locationRepository: LocationRepository): ObserveSectionsUseCase {
        return ObserveSectionsUseCase(locationRepository = locationRepository)
    }

    @Provides
    fun provideUpdateSectionNameUseCase(locationRepository: LocationRepository): UpdateSectionNameUseCase {
        return UpdateSectionNameUseCase(locationRepository = locationRepository)
    }

    @Provides
    fun provideWriteBitmapToFile(
        absolutelyFilePath: AbsolutelyFilePath,
        getBitmapFromUri: GetBitmapFromUri
    ): WriteBitmapToFile {
        return WriteBitmapToFile(
            absolutelyPath = absolutelyFilePath.absolutelyPath,
            getBitmapFromUri = getBitmapFromUri
        )
    }

    @Provides
    fun provideGetBitmapFromUri(contentResolver: ContentResolver): GetBitmapFromUri {
        return GetBitmapFromUri(contentResolver = contentResolver)
    }

    @Provides
    fun provideGetPhotoFileNameUseCase(
        calendar: Calendar,
        simpleDateFormat: SimpleDateFormat
    ): GetPhotoFileNameUseCase {
        return GetPhotoFileNameUseCase(
            calendar = calendar,
            simpleDateFormat = simpleDateFormat
        )
    }

    @Provides
    fun provideCalendar(): Calendar {
        return Calendar.getInstance()
    }

    @Provides
    fun provideSimpleDateFormat(locale: Locale): SimpleDateFormat {
        return SimpleDateFormat("yyyyMMddHHmmss", locale)
    }

    @Provides
    fun provideLocale(): Locale {
        return Locale.getDefault()
    }
}