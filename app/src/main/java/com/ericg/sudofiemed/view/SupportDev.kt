package com.ericg.sudofiemed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.databinding.FragmentSupportDevBinding

class SupportDev: Fragment() {
    var supportDevBinding: FragmentSupportDevBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        supportDevBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_support_dev, null, false)
        return supportDevBinding?.root
    }
}
