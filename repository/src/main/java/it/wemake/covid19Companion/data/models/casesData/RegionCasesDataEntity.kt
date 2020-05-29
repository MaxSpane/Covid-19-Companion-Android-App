package it.wemake.covid19Companion.data.models.casesData

data class RegionCasesDataEntity (
    val displayName: String,
    val updated: Long,
    val totalConfirmed: Int?,
    val totalDeaths: Int?,
    val totalRecovered: Int?,
    val parentCountryName: String
)