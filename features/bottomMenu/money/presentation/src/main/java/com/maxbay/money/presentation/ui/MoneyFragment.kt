package com.maxbay.money.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.maxbay.money.presentation.R
import com.maxbay.money.presentation.databinding.FragmentMoneyBinding

class MoneyFragment: Fragment(R.layout.fragment_money) {
    private var binding: FragmentMoneyBinding? = null

    companion object {
        fun newInstance(): MoneyFragment {
            return MoneyFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoneyBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}