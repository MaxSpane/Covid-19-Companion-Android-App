package it.wemake.covid19Companion.data.models

import it.wemake.covid19Companion.data.models.CountryCasesEntity
import org.jetbrains.annotations.Nullable

data class AreaCasesDataEntity(
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