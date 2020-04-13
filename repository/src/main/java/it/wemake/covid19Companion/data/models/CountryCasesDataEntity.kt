package it.wemake.covid19Companion.data.models

data class CountryCasesDataEntity (
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