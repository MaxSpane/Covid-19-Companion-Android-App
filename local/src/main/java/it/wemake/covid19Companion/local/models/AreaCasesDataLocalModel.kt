package it.wemake.covid19Companion.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.Nullable

@Entity(tableName = "area_cases_data")
data class AreaCasesDataLocalModel (
    @PrimaryKey
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