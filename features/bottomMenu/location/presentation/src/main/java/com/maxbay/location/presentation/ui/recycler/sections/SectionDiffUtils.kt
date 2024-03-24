package com.maxbay.location.presentation.ui.recycler.sections

import androidx.recyclerview.widget.DiffUtil
import com.maxbay.location.presentation.models.SectionUi

class SectionDiffUtils: DiffUtil.ItemCallback<SectionUi>() {
    override fun areItemsTheSame(oldItem: SectionUi, newItem: SectionUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SectionUi, newItem: SectionUi): Boolean {
        return oldItem == newItem
    }
}