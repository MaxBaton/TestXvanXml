package com.maxbay.money.presentation.api

import androidx.fragment.app.Fragment
import com.maxbay.money.api.MoneyFeatureApi
import com.maxbay.money.presentation.R
import com.maxbay.money.presentation.ui.MoneyFragment

class MoneyFeatureApiImpl: MoneyFeatureApi {
    override fun fragment(): Fragment = MoneyFragment.newInstance()

    override fun tabIconId(): Int = R.drawable.ic_money
}