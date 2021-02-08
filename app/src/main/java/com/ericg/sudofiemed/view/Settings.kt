package com.ericg.sudofiemed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.databinding.FragmentSettingsBinding

class Settings : Fragment() {
    var settingsBinding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_settings, null, false)
        return settingsBinding?.root
    }
}