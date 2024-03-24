package com.maxbay.table.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.maxbay.table.presentation.R
import com.maxbay.table.presentation.databinding.FragmentTableBinding

class TableFragment: Fragment(R.layout.fragment_table) {
    private var binding: FragmentTableBinding? = null

    companion object {
        fun newInstance(): TableFragment {
            return TableFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTableBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}