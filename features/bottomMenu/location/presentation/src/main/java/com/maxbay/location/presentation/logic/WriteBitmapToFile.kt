package com.maxbay.location.presentation.logic

import android.graphics.Bitmap
import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class WriteBitmapToFile(
    private val absolutelyPath: String,
    private val getBitmapFromUri: GetBitmapFromUri,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun write(
        fileName: String,
        locationId: Int,
        uri: Uri
    ) {
        return withContext(dispatcher) {
            val bitmap = getBitmapFromUri.get(uri = uri)

            val folder = createFolderIfNeed(folderName = GetBaseFolderName.get(locationId))
            val file = File(folder, fileName)

            FileOutputStream(file).use { fos ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            }
        }
    }

    private fun createFolderIfNeed(folderName: String): File {
        val folderPath = absolutelyPath + File.separator + folderName
        val folder = File(folderPath)
        if (!folder.exists()) {
            folder.mkdir()
        }
        return folder
    }
}