package it.weMake.covid19Companion.models.casesData

data class RegionCasesData (
    val displayName: String,
    val updated: Long,
    val totalConfirmed: Int?,
    val totalDeaths: Int?,
    val totalRecovered: Int?,
    val parentCountryName: String
)