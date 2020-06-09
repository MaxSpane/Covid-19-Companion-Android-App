package it.weMake.covid19Companion.broadcastReceivers

import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import dagger.android.DaggerBroadcastReceiver
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.utils.LOCATION_WASH_HANDS_NOTIFICATION_ID
import it.weMake.covid19Companion.utils.showReminderNotification
import it.wemake.covid19Companion.domain.usecases.GetUseCustomNotificationToneUseCase
import it.wemake.covid19Companion.domain.usecases.GetUsernameUseCase
import it.wemake.covid19Companion.domain.usecases.GetWashHandsReminderLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class GeofenceBroadcastReceiver : DaggerBroadcastReceiver() {

    @Inject
    lateinit var getWashHandsReminderLocationUseCase: GetWashHandsReminderLocationUseCase

    @Inject
    lateinit var getUseCustomNotificationToneUseCase: GetUseCustomNotificationToneUseCase

    @Inject
    lateinit var getUsernameUseCase: GetUsernameUseCase

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        if (geofencingEvent.hasError()) {
//            val errorMessage = GeofenceStatusCodes.getErrorString(geofencingEvent.errorCode)
//            Log.e(TAG, errorMessage)
            return
        }

        // Get the transition type.
        val geofenceTransition = geofencingEvent.geofenceTransition

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_DWELL) {

            // Get the geofences that were triggered. A single event can trigger
            // multiple geofences.
            val triggeringGeofences = geofencingEvent.triggeringGeofences

            // Get the transition details as a String.
//            val geofenceTransitionDetails = getGeofenceTransitionDetails(
//                this,
//                geofenceTransition,
//                triggeringGeofences
//            )

            for (triggeringGeofence in triggeringGeofences){

                GlobalScope.launch(Dispatchers.IO) {
                    getWashHandsReminderLocationUseCase(triggeringGeofence.requestId.toInt()).collect {
                        showReminderNotification(
                            context,
                            LOCATION_WASH_HANDS_NOTIFICATION_ID,
                            context.getString(R.string.placeholder_location_wash_hands_reminder, it.name),
                            getUseCustomNotificationToneUseCase(),
                            getUsernameUseCase()
                        )

                    }
                }
            }

        } else {
            // Log the error.
//            Log.e(TAG, getString(R.string.geofence_transition_invalid_type,
//                geofenceTransition))
        }

    }
}
