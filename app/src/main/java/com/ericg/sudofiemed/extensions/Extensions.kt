package com.ericg.sudofiemed.extensions

import android.app.Activity
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

object Extensions {
    enum class ToastDuration {
        SHORT, LONG
    }

    fun Fragment.toast(msg: String, duration: ToastDuration) {
        if (duration == ToastDuration.LONG) {
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }
    }

    fun Activity.toast(msg: String, duration: ToastDuration) {
        if (duration == ToastDuration.LONG) {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    fun Activity.requestAppPermissions(permissions: Array<String>, requestCode: Int){
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }
}