package it.weMake.covid19Companion.models.casesData

import it.weMake.covid19Companion.models.CountryInfo

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
    val continent: String
)