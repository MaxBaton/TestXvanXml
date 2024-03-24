package com.maxbay.location.presentation.logic

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileInputStream

class ReadBitmapFromFile(private val context: Context) {
    fun read(fileName: String, locationId: Int): Bitmap? {
        return try {
            val folder = getPhotoLocationFolder(locationId)
            if (folder != null) {
                val file = File(folder, fileName)
                FileInputStream(file).use { fis ->
                    BitmapFactory.decodeStream(fis)
                }
            }else {
                null
            }
        }catch (e: Exception) {
            null
        }
    }

    private fun getPhotoLocationFolder(locationId: Int): File? {
        val path = context.filesDir.absolutePath
        val folderPath = path + File.separator + GetBaseFolderName.get(locationId = locationId)
        val folder = File(folderPath)
        return if (folder.exists()) {
            folder
        }else {
            null
        }
    }
}