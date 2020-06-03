package it.weMake.covid19Companion.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import it.weMake.covid19Companion.broadcastReceivers.GeofenceBroadcastReceiver
import it.weMake.covid19Companion.models.washHandsReminderLocations.WashHandsReminderLocation

class GeofencingUtils(private val context: Context) {

    private var geofencingClient = LocationServices.getGeofencingClient(context)

    private val geofencePendingIntent: PendingIntent by lazy {
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling
        // addGeofences() and removeGeofences().
        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun addGeoFences(reminderLocations: List<WashHandsReminderLocation>){
        val geofences = ArrayList<Geofence>()

        for (reminderLocation in reminderLocations){
            if (reminderLocation.enabled)
                geofences.add(createGeofence(reminderLocation))
        }

        if (geofences.isEmpty())
            return

        val getGeofencingRequest = getGeofencingRequest(geofences)

        geofencingClient.addGeofences(getGeofencingRequest, geofencePendingIntent)?.run {
            addOnSuccessListener {
                // Geofences added
                // ...
                showShortToast(context, "Added geofence!!!!")
            }
            addOnFailureListener {
                // Failed to add geofences
                // ...
                showShortToast(context, "Failed to add geofence!!!!")
                it.printStackTrace()
            }
        }
    }

    fun removeGeofences(reminderLocationsIds: List<String>){
        geofencingClient.removeGeofences(reminderLocationsIds)?.run {
            addOnSuccessListener {
                // Geofences removed
                // ...
            }
            addOnFailureListener {
                // Failed to remove geofences
                // ...
            }
        }
    }

    private fun createGeofence(reminderLocation: WashHandsReminderLocation): Geofence =
        Geofence.Builder()
            // Set the request ID of the geofence. This is a string to identify this
            // geofence.
            .setRequestId(reminderLocation.id.toString())

            // Set the circular region of this geofence.
            .setCircularRegion(
                reminderLocation.lat,
                reminderLocation.lng,
                GEOFENCE_RADIUS_IN_METERS
            )

            // Set the expiration duration of the geofence. This geofence gets automatically
            // removed after this period of time.
            .setExpirationDuration(Geofence.NEVER_EXPIRE)

            // Set the transition types of interest. Alerts are only generated for these transition.
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_DWELL)
            .setLoiteringDelay(GEOFENCE_LOITERING_DELAY_IN_MILLISECONDS)

            .setNotificationResponsiveness(ONE_MINUTE_IN_MILLI.toInt() * 3)
            // Create the geofence.
            .build()

    private fun getGeofencingRequest(geofences: List<Geofence>): GeofencingRequest =
        GeofencingRequest.Builder().apply {
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER or GeofencingRequest.INITIAL_TRIGGER_DWELL)
            addGeofences(geofences)
        }.build()

}