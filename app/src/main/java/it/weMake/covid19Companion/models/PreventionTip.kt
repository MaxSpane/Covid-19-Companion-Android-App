package it.weMake.covid19Companion.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PreventionTip(
    val title: String,
    val preventionTip: String,
    val preventionTipWhy: String,
    val iconId: String
): Parcelable