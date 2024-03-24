package com.maxbay.location.presentation.ui.recycler.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.maxbay.location.presentation.databinding.LocationItemBinding
import com.maxbay.location.presentation.models.setcionData.LocationUi

class LocationAdapter(
    private val onAddPhotos: (locationId: Int) -> Unit
): ListAdapter<LocationUi, LocationViewHolder>(LocationDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            binding = LocationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onAddPhotos = onAddPhotos
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}