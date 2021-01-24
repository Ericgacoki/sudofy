package com.ericg.sudofiemed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.databinding.FragmentOnBoard1Binding

class OnBoard1 : Fragment() {
    private var onBoard1Binding: FragmentOnBoard1Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onBoard1Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_on_board1, container, false)
        return onBoard1Binding?.root.apply {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        onBoard1Binding = null
    }
}