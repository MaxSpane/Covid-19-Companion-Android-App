package it.wemake.covid19Companion.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.Nullable

@Entity(tableName = "region_cases_data")
data class RegionCasesDataLocalModel (
    @PrimaryKey
    val displayName: String,
    val updated: Long,
    val totalConfirmed: Int?,
    val totalDeaths: Int?,
    val totalRecovered: Int?,
    val parentCountryName: String
)