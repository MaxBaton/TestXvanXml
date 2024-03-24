package com.maxbay.location.presentation.ui.recycler.sections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.maxbay.location.presentation.databinding.SectionItemBinding
import com.maxbay.location.presentation.models.setcionData.SectionUi

class SectionAdapter(
    private val onChangeSectionName: (sectionId: Int, name: String) -> Unit,
    private val onAddPhotos: (locationId: Int) -> Unit,
    private val onLongPhotoClick: (id: Int, isInDeleteMode: Boolean) -> Unit,
    private val onPhotoClick: (id: Int, isInDeleteMode: Boolean) -> Unit,
    private val onDeletePhotos: (locationId: Int) -> Unit
): ListAdapter<SectionUi, SectionViewHolder>(SectionDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        return SectionViewHolder(
            binding = SectionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onChangeSectionName = onChangeSectionName,
            onAddPhotos = onAddPhotos,
            onLongPhotoClick = onLongPhotoClick,
            onPhotoClick = onPhotoClick,
            onDeletePhotos = onDeletePhotos
        )
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}