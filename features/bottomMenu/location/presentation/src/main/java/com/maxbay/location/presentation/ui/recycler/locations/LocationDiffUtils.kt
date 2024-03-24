package com.maxbay.location.presentation.ui.recycler.locations

import androidx.recyclerview.widget.DiffUtil
import com.maxbay.location.presentation.models.setcionData.LocationUi

class LocationDiffUtils: DiffUtil.ItemCallback<LocationUi>() {
    override fun areItemsTheSame(oldItem: LocationUi, newItem: LocationUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationUi, newItem: LocationUi): Boolean {
        return oldItem == newItem
    }
}