package com.ericg.sudofiemed.view

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.databinding.FragmentOnBoard3Binding

class OnBoard3 : Fragment() {
    private var onBoard3Binding: FragmentOnBoard3Binding? = null
    // lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        onBoard3Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_on_board3, container, false)
        return onBoard3Binding?.root.apply {
            onBoard3Binding?.btnDoneOnBoard?.setOnClickListener {
                showAgreementDialog()
            }
        }
    }

    private fun showAgreementDialog() {
        val dialog = AlertDialog.Builder(requireContext()).create()
        dialog.apply {
            setView(layoutInflater.inflate(R.layout.agreement_dialog, null))
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        onBoard3Binding = null
    }
}