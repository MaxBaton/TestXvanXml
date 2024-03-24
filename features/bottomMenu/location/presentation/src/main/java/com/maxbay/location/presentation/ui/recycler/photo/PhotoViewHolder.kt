package com.maxbay.location.presentation.ui.recycler.photo

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maxbay.location.presentation.databinding.PhotoItemBinding
import com.maxbay.location.presentation.models.setcionData.PhotoDeleteMode
import com.maxbay.location.presentation.models.setcionData.PhotoDeleteMode.*
import com.maxbay.location.presentation.models.setcionData.PhotoUi

class PhotoViewHolder(
    private val binding: PhotoItemBinding,
    private val onLongClick: (id: Int, isInDeleteMode: Boolean) -> Unit,
    private val onClick: (id: Int, isInDeleteMode: Boolean) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind(photo: PhotoUi) {
        photo.bitmap?.let { bitmap ->
            Glide
                .with(itemView.context)
                .load(bitmap)
                .into(binding.imageViewPhoto)
        }

        binding.root.setOnLongClickListener {
            onLongClick.invoke(photo.id, photo.deleteMode == IN_DELETE_MODE)
            true
        }

        binding.root.setOnClickListener {
            onClick.invoke(photo.id, photo.deleteMode == IN_DELETE_MODE)
        }

        configImagesByDeleteMode(deleteMode = photo.deleteMode)
    }

    private fun configImagesByDeleteMode(deleteMode: PhotoDeleteMode) {
        with(binding) {
            when(deleteMode) {
                NONE -> {
                    imageViewDeleteMode.visibility = View.GONE
                    imageViewPhotoInDeleteMode.visibility = View.GONE
                }
                INCLUDE_DELETE_MODE -> {
                    imageViewDeleteMode.visibility = View.VISIBLE
                    imageViewPhotoInDeleteMode.visibility = View.GONE
                }
                IN_DELETE_MODE -> {
                    imageViewDeleteMode.visibility = View.GONE
                    imageViewPhotoInDeleteMode.visibility = View.VISIBLE
                }
            }
        }
    }
}