package com.maxbay.location.presentation.ui.recycler.photo

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maxbay.location.presentation.databinding.PhotoItemBinding
import com.maxbay.location.presentation.models.setcionData.PhotoUi

class PhotoViewHolder(
    private val binding: PhotoItemBinding,
    private val onLongClick: (id: Int) -> Unit,
    private val onClick: (id: Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind(photo: PhotoUi) {
        photo.bitmap?.let { bitmap ->
            Glide
                .with(itemView.context)
                .load(bitmap)
                .into(binding.imageViewPhoto)
        }

        binding.root.setOnLongClickListener {
            onLongClick.invoke(photo.id)
            true
        }

        binding.root.setOnClickListener {
            onClick.invoke(photo.id)
        }
    }
}