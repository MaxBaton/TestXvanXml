package com.maxbay.location.presentation.ui.recycler.locations

import androidx.recyclerview.widget.RecyclerView
import com.maxbay.location.presentation.databinding.LocationItemBinding
import com.maxbay.location.presentation.models.setcionData.LocationUi
import com.maxbay.location.presentation.ui.recycler.photo.PhotoAdapter

class LocationViewHolder(
    private val binding: LocationItemBinding,
    private val onAddPhotos: (locationId: Int) -> Unit,
    private val onLongPhotoClick: (id: Int, isInDeleteMode: Boolean) -> Unit,
    private val onPhotoClick: (id: Int, isInDeleteMode: Boolean) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    private val photoAdapter by lazy {
        PhotoAdapter(
            onLongClick = onLongPhotoClick,
            onClick = onPhotoClick
        )
    }

    fun bind(location: LocationUi) {
        binding.tvLocationName.text = location.name

        binding.btnAdd.setOnClickListener {
            onAddPhotos.invoke(location.id)
        }

        binding.recyclerViewPhotos.adapter = photoAdapter
        photoAdapter.submitList(location.photos)
    }
}