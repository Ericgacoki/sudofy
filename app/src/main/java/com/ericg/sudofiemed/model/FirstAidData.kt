package com.ericg.sudofiemed.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FirstAidData(
    val title: String,
    val image: Int,
    val desc: String
) : Parcelable