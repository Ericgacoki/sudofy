package com.ericg.sudofiemed.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.databinding.FragmentDetailsBinding
import com.ericg.sudofiemed.model.FirstAidData

class Details : Fragment() {

    private val args by navArgs<DetailsArgs>()

    private var _detailsBinding: FragmentDetailsBinding? = null
    private val detailsBinding get() = _detailsBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _detailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        fun handleClicks() {

            detailsBinding.apply {
                detailsBtnBack.setOnClickListener {
                    requireActivity().onBackPressed()
                }
                detailsBtnShare.setOnClickListener {
                    // share()
                }
            }
        }

        return detailsBinding.root.apply {
            handleClicks()
            val item: FirstAidData = args.clickedItem

            detailsBinding.apply {
                detailsImage.clipToOutline = true
                detailsImage.setImageResource(item.image)
                detailsTitleText.text = item.title
                detailsBodyText.text = item.desc
            }
        }
    }
}