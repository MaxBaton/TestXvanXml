package com.maxbay.location.presentation.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class ConfirmDeleteDialogFragment: DialogFragment() {
    companion object{
        const val CONFIRM_DIALOG_REQUEST_KEY = "CONFIRM_DIALOG_REQUEST_KEY"

        fun newInstance() = ConfirmDeleteDialogFragment()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext()).apply {
            setTitle("deleting")
            setMessage("delete selected parts")
            setPositiveButton("delete", null)
            setNegativeButton("cancel", null)
        }.create()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (
            tag != null &&
            tag == ConfirmDeleteDialogFragment::class.simpleName
            && manager.findFragmentByTag(tag) == null
        ) {
            super.show(manager, tag)
        }
    }

    override fun onResume() {
        super.onResume()

        dialog?.let { setOnClickListener(dialog = it) }
    }

    private fun setOnClickListener(dialog: Dialog) {
        dialog.setCancelable(false)
        val alertDialog = dialog as AlertDialog
        val btnExit = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        btnExit.setOnClickListener { view: View? ->
            dialog.dismiss()

            parentFragmentManager.setFragmentResult(
                CONFIRM_DIALOG_REQUEST_KEY,
                bundleOf()
            )
        }
    }
}