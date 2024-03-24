package com.maxbay.table.presentation.api

import androidx.fragment.app.Fragment
import com.maxbay.table.api.TableFeatureApi
import com.maxbay.table.presentation.R
import com.maxbay.table.presentation.ui.TableFragment

class TableFeatureApiImpl: TableFeatureApi {
    override fun fragment(): Fragment = TableFragment.newInstance()

    override fun tabIconId(): Int = R.drawable.ic_table
}