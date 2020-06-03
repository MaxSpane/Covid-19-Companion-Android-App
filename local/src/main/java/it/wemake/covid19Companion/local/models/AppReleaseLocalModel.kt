package it.wemake.covid19Companion.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_releases")
data class AppReleaseLocalModel(
    @PrimaryKey
    val versionName: String,
    val versionDetails: String
)