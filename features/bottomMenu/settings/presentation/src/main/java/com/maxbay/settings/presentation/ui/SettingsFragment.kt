package com.maxbay.settings.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.maxbay.settings.presentation.R
import com.maxbay.settings.presentation.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment(R.layout.fragment_settings) {
    private var binding: FragmentSettingsBinding? = null

    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}