package com.ericg.sudofiemed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.databinding.FragmentOnBoard2Binding

class OnBoard2 : Fragment() {
    private var onBoard2Binding: FragmentOnBoard2Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onBoard2Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_on_board2, container, false)
        return onBoard2Binding?.root.apply {
            // access view here
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        onBoard2Binding = null
    }
}