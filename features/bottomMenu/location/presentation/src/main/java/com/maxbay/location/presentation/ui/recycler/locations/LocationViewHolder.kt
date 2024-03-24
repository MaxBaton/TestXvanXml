package com.maxbay.location.presentation.ui.recycler.locations

import androidx.recyclerview.widget.RecyclerView
import com.maxbay.location.presentation.databinding.LocationItemBinding
import com.maxbay.location.presentation.models.setcionData.LocationUi

class LocationViewHolder(
    private val binding: LocationItemBinding,
    private val onAddPhotos: (locationId: Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind(location: LocationUi) {
        binding.tvLocationName.text = location.name

        binding.btnAdd.setOnClickListener {
            onAddPhotos.invoke(location.id)
        }
    }
}