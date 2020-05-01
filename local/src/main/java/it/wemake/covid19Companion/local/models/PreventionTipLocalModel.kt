package it.wemake.covid19Companion.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prevention_tips")
data class PreventionTipLocalModel(
    val title: String,
    val preventionTip: String,
    val preventionTipWhy: String,
    @PrimaryKey
    val iconId: String
)