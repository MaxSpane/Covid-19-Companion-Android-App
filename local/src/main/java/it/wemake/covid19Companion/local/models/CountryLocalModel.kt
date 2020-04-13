package it.wemake.covid19Companion.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.Nullable

@Entity(tableName = "countries")
data class CountryLocalModel(
    @PrimaryKey
    val name: String,
    val iso2: String?
)