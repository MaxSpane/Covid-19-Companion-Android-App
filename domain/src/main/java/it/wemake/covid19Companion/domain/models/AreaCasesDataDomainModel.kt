package it.wemake.covid19Companion.domain.models

data class AreaCasesDataDomainModel(
    val id: String,
    val displayName: String,
    val lastUpdated: String?,
    val totalConfirmed: Int?,
    val totalDeaths: Int?,
    val totalRecovered: Int?,
    val totalConfirmedDelta: Int?,
    val totalDeathsDelta: Int?,
    val totalRecoveredDelta: Int?,
    val parentId: String?,
    val hasAreasData: Boolean
)