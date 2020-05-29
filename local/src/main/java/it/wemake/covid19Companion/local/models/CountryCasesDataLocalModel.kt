package it.wemake.covid19Companion.local.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import it.wemake.covid19Companion.data.models.CountryInfoEntity
import org.jetbrains.annotations.Nullable

@Entity(tableName = "countries_cases_data")
data class CountryCasesDataLocalModel (
    @PrimaryKey
    val displayName: String,
    val totalConfirmed: Int?,
    val totalDeaths: Int?,
    val totalRecovered: Int?,
    val totalConfirmedDelta: Int?,
    val totalDeathsDelta: Int?,
    val totalRecoveredDelta: Int?,
    val updated: Long,
    val continent: String,
    @Embedded
    val countryInfo: CountryInfoLocalModel,
    val casesPerOneMillion: Double?,
    val deathsPerOneMillion: Double?,
    val recoveredPerOneMillion: Double?,
    val hasRegionalCasesData: Boolean
)


data class CountryInfoLocalModel (
    val iso2: String,
    val iso3: String
)