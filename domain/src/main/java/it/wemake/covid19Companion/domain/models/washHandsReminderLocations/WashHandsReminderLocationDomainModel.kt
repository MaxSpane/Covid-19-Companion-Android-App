package it.wemake.covid19Companion.domain.models.washHandsReminderLocations

data class WashHandsReminderLocationDomainModel(
    val id: Int,
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val enabled: Boolean
)