package it.weMake.covid19Companion.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.android.DaggerBroadcastReceiver
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.utils.*
import it.wemake.covid19Companion.domain.usecases.GetUseCustomNotificationToneUseCase
import it.wemake.covid19Companion.domain.usecases.GetUsernameUseCase
import it.wemake.covid19Companion.domain.usecases.GetWashHandsIntervalUseCase
import javax.inject.Inject

class WashHandsReminderBroadcast : DaggerBroadcastReceiver() {

    @Inject
    lateinit var getWashHandsIntervalUseCase: GetWashHandsIntervalUseCase

    @Inject
    lateinit var getUseCustomNotificationToneUseCase: GetUseCustomNotificationToneUseCase

    @Inject
    lateinit var getUsernameUseCase: GetUsernameUseCase

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        showReminderNotification(
            context,
            WASH_HANDS_NOTIFICATION_ID,
            context.getString(R.string.text_wash_hands_reminder),
            getUseCustomNotificationToneUseCase(),
            getUsernameUseCase()
        )

        val time = minutesToMilliSecs(getWashHandsIntervalUseCase())
        createCancelWashHandsAlarm(context, time)
    }

}