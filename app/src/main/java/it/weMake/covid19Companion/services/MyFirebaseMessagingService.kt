package it.weMake.covid19Companion.services

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.android.AndroidInjection
import dagger.android.DaggerService
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.utils.*
import it.wemake.covid19Companion.domain.usecases.SetDailyMotivationUseCase
import javax.inject.Inject


class MyFirebaseMessagingService : FirebaseMessagingService(){

    private val TAG = "FirebaseMsgService"

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    @Inject
    lateinit var setDailyMotivationUseCase: SetDailyMotivationUseCase

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.from)

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            val notificationData = remoteMessage.data
            Log.d(TAG, "Message data payload: " + notificationData)

            when(notificationData[FCM_KEY_NOTIFICATION_TYPE]){

                FCM_NOTIFICATION_TYPE_DAILY_MOTIVATION -> {
                    val dailyMotivation = notificationData[FCM_KEY_DAILY_MOTIVATION_MESSAGE]!!
                    setDailyMotivationUseCase(dailyMotivation)
                    createDefaultNotificationChannel(this)
                    showDefaultNotification(
                        this,
                        DAILY_MOTIVATION_NOTIFICATION_ID,
                        getString(R.string.title_motivation_notification),
                        dailyMotivation)
                }

                FCM_NOTIFICATION_TYPE_APP_UPDATE_AVAILABLE -> {}

            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body)
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

}
