package com.ericg.sudofiemed.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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

            DataStorePrefs(requireContext()).showOnBoard.observe(viewLifecycleOwner, {
                showOnBoardScreen = it
            })

            splashBinding?.tvSplash?.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.from_btm))

            splashOut()
        }
    }

    @Suppress("DEPRECATION")
    private fun splashOut() {
        Handler().postDelayed({

            if (showOnBoardScreen) {
                findNavController().navigate(R.id.action_splash_to_onBoardViewPager)
            } else {
                findNavController().navigate(R.id.action_splash_to_firstAids)
            }
        }, 3000)
    }

    override fun onDestroy() {
        /** prevent memory leaks*/
        splashBinding = null
        super.onDestroy()
    }
}