package it.wemake.covid19Companion.data.models.washHandsReminderLocations

data class WashHandsReminderLocationEntity(
    val id: Int,
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val enabled: Boolean
)