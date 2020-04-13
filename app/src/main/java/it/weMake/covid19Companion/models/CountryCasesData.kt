package it.weMake.covid19Companion.models

data class CountryCasesData(
    val id: String,
    val displayName: String,
    val totalConfirmed: Int?,
    val totalDeaths: Int?,
    val totalRecovered: Int?,
    val totalConfirmedDelta: Int?,
    val totalDeathsDelta: Int?,
    val totalRecoveredDelta: Int?,
    val hasAreasData: Boolean,
    val iso2: String?
)