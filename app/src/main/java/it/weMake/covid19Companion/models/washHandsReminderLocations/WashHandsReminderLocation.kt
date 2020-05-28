package it.weMake.covid19Companion.models.washHandsReminderLocations

data class WashHandsReminderLocation(
    var id: Int = 0,
    var name: String = "",
    var address: String = "",
    var lat: Double = 0.0,
    var lng: Double = 0.0,
    var enabled: Boolean = true
)