package com.maxbay.studentapp.ui.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

private const val ZERO_INT_VALUE = 0

class SpaceVerticalDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            left = ZERO_INT_VALUE
            right = ZERO_INT_VALUE
            bottom = spaceSize
        }
    }
}