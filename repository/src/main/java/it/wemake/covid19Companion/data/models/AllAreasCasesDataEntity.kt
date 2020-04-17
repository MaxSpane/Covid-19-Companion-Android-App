package it.wemake.covid19Companion.data.models

data class AllAreasCasesDataEntity(
    val id: String,
    val displayName: String,
    val areas: List<AllAreasCasesDataEntity>,
    val lastUpdated: String?,
    val totalConfirmed: Int?,
    val totalDeaths: Int?,
    val totalRecovered: Int?,
    val totalConfirmedDelta: Int?,
    val totalDeathsDelta: Int?,
    val totalRecoveredDelta: Int?,
    val parentId: String?
)