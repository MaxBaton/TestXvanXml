package com.maxbay.location.presentation.ui.fragment

import android.app.Activity
import android.content.Intent
import android.media.AudioManager.AudioRecordingCallback
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maxbay.location.presentation.R
import com.maxbay.location.presentation.databinding.FragmentLocationBinding
import com.maxbay.location.presentation.di.DaggerLocationComponent
import com.maxbay.location.presentation.di.LocationFeatureDepsProvider
import com.maxbay.location.presentation.models.screens.Screen
import com.maxbay.location.presentation.models.setcionData.SectionUi
import com.maxbay.location.presentation.ui.recycler.sections.SectionAdapter
import com.maxbay.location.presentation.viewModel.LocationViewModel
import com.maxbay.testxvan.ui.utils.showShortToast

class LocationFragment: Fragment(R.layout.fragment_location) {
    private var binding: FragmentLocationBinding? = null
    private var locationId: Int = -1

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
            },
            onAddPhotos = { locationId ->
                locationViewModel.openGallery(locationId = locationId)
            }
        )
    }
    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.let { data ->
                if (locationId != -1) {
                    val photosUri = mutableListOf<Uri>()

                    val cnt = data.clipData?.itemCount ?: 0
                    for (i in 0 until cnt) {
                        val uri: Uri = data.clipData!!.getItemAt(i).uri
                        photosUri.add(uri)
                    }

                    locationViewModel.savePhotos(
                        locationId = locationId,
                        photosUri = photosUri
                    )
                }
            }
        }
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
        locationViewModel.screen.observe(viewLifecycleOwner, this::observeScreen)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null

        locationViewModel.clearState()
    }

    private fun observeSections(sections: List<SectionUi>) {
        sectionAdapter.submitList(sections)
    }

    private fun observeScreen(screen: Screen) {
        when(screen) {
            Screen.None -> Unit
            is Screen.GalleryScreen -> {
                locationId = screen.locationId
                openGallery()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        galleryLauncher.launch(intent)
    }
}