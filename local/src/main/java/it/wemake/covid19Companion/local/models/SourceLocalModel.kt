package it.wemake.covid19Companion.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sources")
data class SourceLocalModel(
    @PrimaryKey
    val sourceName: String,
    val sourceDescription: String,
    val resources: String,
    val sourceExternalLink: String
)