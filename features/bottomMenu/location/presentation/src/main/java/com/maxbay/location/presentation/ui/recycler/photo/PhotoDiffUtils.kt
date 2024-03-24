package com.maxbay.location.presentation.ui.recycler.photo

import androidx.recyclerview.widget.DiffUtil
import com.maxbay.location.presentation.models.setcionData.PhotoUi

class PhotoDiffUtils: DiffUtil.ItemCallback<PhotoUi>() {
    override fun areItemsTheSame(oldItem: PhotoUi, newItem: PhotoUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoUi, newItem: PhotoUi): Boolean {
        return oldItem == newItem
    }
}