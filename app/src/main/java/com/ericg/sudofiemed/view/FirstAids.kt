package com.ericg.sudofiemed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.databinding.FragmentFirstAidsBinding

class FirstAids : Fragment() {
    private var firstAidsBinding: FragmentFirstAidsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firstAidsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_first_aids, container, false)
        return firstAidsBinding?.root?.apply {

        }
    }
}