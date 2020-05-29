package it.weMake.covid19Companion.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryInfo(
    val iso2: String,
    val iso3: String
): Parcelable