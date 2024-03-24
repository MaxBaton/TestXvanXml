package com.maxbay.location.presentation.ui.recycler.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.maxbay.location.presentation.databinding.PhotoItemBinding
import com.maxbay.location.presentation.models.setcionData.PhotoUi

class PhotoAdapter(
    private val onLongClick: (id: Int, isInDeleteMode: Boolean) -> Unit,
    private val onClick: (id: Int, isInDeleteMode: Boolean) -> Unit
): ListAdapter<PhotoUi, PhotoViewHolder>(PhotoDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            binding = PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onLongClick = onLongClick,
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}