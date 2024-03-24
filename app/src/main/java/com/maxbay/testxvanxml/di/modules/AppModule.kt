package com.maxbay.testxvanxml.di.modules

import android.content.ContentResolver
import android.content.Context
import com.maxbay.location.presentation.models.bitmap.AbsolutelyFilePath
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContentResolver(context: Context): ContentResolver {
        return context.contentResolver
    }

    @Provides
    fun provideAbsolutelyFilePath(context: Context): AbsolutelyFilePath {
        return AbsolutelyFilePath(absolutelyPath = context.filesDir.absolutePath)
    }
}