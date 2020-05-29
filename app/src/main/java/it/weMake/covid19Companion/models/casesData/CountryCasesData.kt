package it.weMake.covid19Companion.models.casesData

import android.os.Parcelable
import it.weMake.covid19Companion.models.CountryInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryCasesData(
    val displayName: String,
    val lastUpdated: Long,
    val countryInfo: CountryInfo,
    val totalConfirmed: Int?,
    val totalDeaths: Int?,
    val totalRecovered: Int?,
    val totalConfirmedDelta: Int?,
    val totalDeathsDelta: Int?,
    val totalRecoveredDelta: Int?,
    val continent: String,
    val casesPerOneMillion: Double?,
    val deathsPerOneMillion: Double?,
    val recoveredPerOneMillion: Double?,
    var hasRegionalCasesData: Boolean
): Parcelable