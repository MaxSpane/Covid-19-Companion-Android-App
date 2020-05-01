package it.wemake.covid19Companion.data.models

data class CountryCasesDataEntity(
    val displayName: String,
    val lastUpdated: Long,
    val countryInfo: CountryInfoEntity,
    val totalConfirmed: Int?,
    val totalDeaths: Int?,
    val totalRecovered: Int?,
    val totalConfirmedDelta: Int?,
    val totalDeathsDelta: Int?,
    val totalRecoveredDelta: Int?,
    val continent: String
)