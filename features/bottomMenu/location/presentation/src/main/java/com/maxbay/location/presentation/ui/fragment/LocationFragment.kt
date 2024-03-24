package com.maxbay.location.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maxbay.location.presentation.R
import com.maxbay.location.presentation.databinding.FragmentLocationBinding
import com.maxbay.location.presentation.di.DaggerLocationComponent
import com.maxbay.location.presentation.di.LocationFeatureDeps
import com.maxbay.location.presentation.di.LocationFeatureDepsProvider
import com.maxbay.location.presentation.models.SectionUi
import com.maxbay.location.presentation.ui.recycler.sections.SectionAdapter
import com.maxbay.location.presentation.viewModel.LocationViewModel

class LocationFragment: Fragment(R.layout.fragment_location) {
    private var binding: FragmentLocationBinding? = null
    private val locationComponent by lazy {
        DaggerLocationComponent
            .builder()
            .addDeps(deps = LocationFeatureDepsProvider.deps)
            .build()
    }
    private val locationViewModel: LocationViewModel by viewModels(
        factoryProducer = { locationComponent.locationViewModelFactory }
    )
    private val sectionAdapter by lazy {
        SectionAdapter(
            onChangeSectionName = { sectionId, newName ->
               locationViewModel.onChangeSectionName(sectionId = sectionId, newName = newName)
            }
        )
    }

    companion object {
        fun newInstance(): LocationFragment {
            return LocationFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLocationBinding.bind(view)

        with(binding ?: return) {
            recyclerViewSections.adapter = sectionAdapter
        }

        locationViewModel.sections.observe(viewLifecycleOwner, this::observeSections)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun observeSections(sections: List<SectionUi>) {
        sectionAdapter.submitList(sections)
    }
}