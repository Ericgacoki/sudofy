package com.ericg.sudofiemed.view

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ericg.sudofiemed.R
import com.ericg.sudofiemed.constants.Constants.permissions
import com.ericg.sudofiemed.constants.Constants.requestCode
import com.ericg.sudofiemed.databinding.ActivityParentBinding
import com.ericg.sudofiemed.extensions.Extensions.requestAppPermissions
import com.ericg.sudofiemed.extensions.Extensions.toast
import com.ericg.sudofiemed.extensions.Extensions.ToastDuration as duration

class ParentActivity : AppCompatActivity() {
    private var parentBinding: ActivityParentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        parentBinding = DataBindingUtil.setContentView(this, R.layout.activity_parent)
    }

    override fun onDestroy() {
        super.onDestroy()
        parentBinding = null
    }

    private var backPressIsActive = false

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        if (backPressIsActive) {
            super.onBackPressed()
        } else {
            toast("press again to exit", duration.LONG)
            backPressIsActive = true
            Handler().postDelayed({ backPressIsActive = false }, 2000)
        }
    }
}