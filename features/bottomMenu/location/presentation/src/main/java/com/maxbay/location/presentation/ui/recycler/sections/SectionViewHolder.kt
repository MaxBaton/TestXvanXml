package com.maxbay.location.presentation.ui.recycler.sections

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maxbay.location.presentation.databinding.SectionItemBinding
import com.maxbay.location.presentation.models.setcionData.SectionUi
import com.maxbay.location.presentation.ui.recycler.locations.LocationAdapter

class SectionViewHolder(
    private val binding: SectionItemBinding,
    private val onChangeSectionName: (sectionId: Int, name: String) -> Unit,
    private val onAddPhotos: (locationId: Int) -> Unit,
    private val onLongPhotoClick: (id: Int, isInDeleteMode: Boolean) -> Unit,
    private val onPhotoClick: (id: Int, isInDeleteMode: Boolean) -> Unit,
    private val onDeletePhotos: (locationId: Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    private val locationAdapter by lazy {
        LocationAdapter(
            onAddPhotos = onAddPhotos,
            onLongPhotoClick = onLongPhotoClick,
            onPhotoClick = onPhotoClick,
            onDeletePhotos = onDeletePhotos
        )
    }

    fun bind(section: SectionUi) {
        binding.etSectionName.setText(section.name, TextView.BufferType.EDITABLE)

        binding.etSectionName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.toString()?.let { text ->
                    onChangeSectionName.invoke(section.id, text)
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        binding.recyclerViewLocations.adapter = locationAdapter
        locationAdapter.submitList(section.locations)
    }
}