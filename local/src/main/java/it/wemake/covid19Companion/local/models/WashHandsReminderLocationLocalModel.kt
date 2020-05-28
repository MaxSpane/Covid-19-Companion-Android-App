package it.wemake.covid19Companion.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wash_hands_reminder_locations")
data class WashHandsReminderLocationLocalModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val enabled: Boolean
)