package com.ericg.sudofiemed.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.databinding.FragmentSplashBinding
import com.ericg.sudofiemed.extensions.Extensions
import com.ericg.sudofiemed.extensions.Extensions.toast
import com.ericg.sudofiemed.model.DataStorePrefs

class Splash : Fragment() {
    private var splashBinding: FragmentSplashBinding? = null
    private var showOnBoardScreen: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        splashBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return splashBinding?.root.apply {
                Glide.with(this!!).load("https://www.google.com/url?sa=i&url=https%3A%2F%2Fthreatpost.com%2Fundeletable-malware-yet-another-android-device%2F157289%2F&psig=AOvVaw01gb5U_XI9MFIpYPq2UljO&ust=1607823311573000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCJjt7bCnx-0CFQAAAAAdAAAAABAO")
                    .into(splashBinding!!.splashImage)

            DataStorePrefs(requireContext()).showOnBoard.observe(viewLifecycleOwner, {
                showOnBoardScreen = it
            })

            splashOut()
        }
    }

    @Suppress("DEPRECATION")
    private fun splashOut() {
        Handler().postDelayed({

            if (showOnBoardScreen) {
                findNavController().navigate(R.id.action_splash_to_onBoardViewPager)
            } else {
                toast("nav to home coming soon", Extensions.ToastDuration.SHORT)
            }

        }, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        /** prevent memory leaks*/
        splashBinding = null
    }
}