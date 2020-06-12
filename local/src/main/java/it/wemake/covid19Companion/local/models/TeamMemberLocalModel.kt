package it.wemake.covid19Companion.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "development_team")
data class TeamMemberLocalModel(
    @PrimaryKey
    val name: String,
    val role: String,
    val about: String,
    val externalLink: String
)