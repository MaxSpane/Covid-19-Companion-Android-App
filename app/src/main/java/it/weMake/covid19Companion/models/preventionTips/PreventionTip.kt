package it.weMake.covid19Companion.models.preventionTips

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PreventionTip(
    val title: String,
    val preventionTip: String,
    val preventionTipWhy: String,
    val iconId: String
): Parcelable