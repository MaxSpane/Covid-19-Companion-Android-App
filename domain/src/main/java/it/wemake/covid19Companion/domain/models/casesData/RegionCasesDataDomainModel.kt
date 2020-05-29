package it.wemake.covid19Companion.domain.models.casesData

data class RegionCasesDataDomainModel (
    val displayName: String,
    val updated: Long,
    val totalConfirmed: Int?,
    val totalDeaths: Int?,
    val totalRecovered: Int?,
    val parentCountryName: String
)