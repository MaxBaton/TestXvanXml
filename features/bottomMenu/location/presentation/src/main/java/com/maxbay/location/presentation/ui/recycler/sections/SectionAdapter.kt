package com.maxbay.location.presentation.ui.recycler.sections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.maxbay.location.presentation.databinding.SectionItemBinding
import com.maxbay.location.presentation.models.SectionUi

class SectionAdapter(
    private val onChangeSectionName: (sectionId: Int, name: String) -> Unit
): ListAdapter<SectionUi, SectionViewHolder>(SectionDiffUtils()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        return SectionViewHolder(
            binding = SectionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onChangeSectionName = onChangeSectionName
        )
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}