package it.wemake.covid19Companion.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.Nullable

data class CountriesCasesDataLocalModel (
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