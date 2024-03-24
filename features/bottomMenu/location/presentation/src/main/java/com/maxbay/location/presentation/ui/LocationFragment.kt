package com.maxbay.location.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.maxbay.location.presentation.R
import com.maxbay.location.presentation.databinding.FragmentLocationBinding

class LocationFragment: Fragment(R.layout.fragment_location) {
    private var binding: FragmentLocationBinding? = null

    companion object {
        fun newInstance(): LocationFragment {
            return LocationFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLocationBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}