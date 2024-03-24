package com.maxbay.location.presentation.ui.recycler.sections

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maxbay.location.presentation.databinding.SectionItemBinding
import com.maxbay.location.presentation.models.SectionUi

class SectionViewHolder(
    private val binding: SectionItemBinding,
    private val onChangeSectionName: (sectionId: Int, name: String) -> Unit
): RecyclerView.ViewHolder(binding.root) {
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
    }
}