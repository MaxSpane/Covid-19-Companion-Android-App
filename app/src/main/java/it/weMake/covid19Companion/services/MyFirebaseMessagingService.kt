package it.weMake.covid19Companion.services

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.android.AndroidInjection
import dagger.android.DaggerService
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.ui.about.AboutActivity
import it.weMake.covid19Companion.ui.about.EXTRA_FROM_NOTIFICATION
import it.weMake.covid19Companion.utils.*
import it.wemake.covid19Companion.domain.models.appReleases.AppReleaseDomainModel
import it.wemake.covid19Companion.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.logging.Handler
import javax.inject.Inject


class MyFirebaseMessagingService : FirebaseMessagingService(){

    private val TAG = "FirebaseMsgService"

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    @Inject
    lateinit var setDailyMotivationUseCase: SetDailyMotivationUseCase

    @Inject
    lateinit var setLatestVersionCodeUseCase: SetLatestVersionCodeUseCase

    @Inject
    lateinit var insertAppReleaseUseCase: InsertAppReleaseUseCase

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

                FCM_NOTIFICATION_TYPE_APP_UPDATE_AVAILABLE -> {
                    setLatestVersionCodeUseCase(notificationData[FCM_KEY_LATEST_APP_VERSION_CODE]!!.toInt())
                    GlobalScope.launch(Dispatchers.IO) {
                        insertAppReleaseUseCase(
                            AppReleaseDomainModel(
                                notificationData[FCM_KEY_LATEST_APP_VERSION_NAME]!!,
                                notificationData[FCM_KEY_LATEST_APP_VERSION_DETAILS]!!
                            )
                        )
                    }

                    val intent = Intent(this, AboutActivity::class.java)
                    intent.putExtra(EXTRA_FROM_NOTIFICATION, true)

                    showDefaultNotification(
                        this,
                        APP_UPDATE_NOTIFICATION_ID,
                        getString(R.string.title_app_update_notification),
                        getString(R.string.message_app_update_notification),
                        intent
                    )
                }

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
