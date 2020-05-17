package it.weMake.covid19Companion.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.android.DaggerBroadcastReceiver
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.utils.*
import it.wemake.covid19Companion.domain.usecases.GetWashHandsIntervalUseCase
import javax.inject.Inject

class WashHandsReminderBroadcast : DaggerBroadcastReceiver() {

    @Inject
    lateinit var getWashHandsIntervalUseCase: GetWashHandsIntervalUseCase

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        showReminderNotification(
            context,
            REMINDERS_NOTIFICATION_CHANNEL_ID,
            WASH_HANDS_NOTIFICATION_ID,
            context.getString(R.string.text_wash_hands_reminder))

        val time = minutesToMilliSecs(getWashHandsIntervalUseCase())
        createCancelWashHandsAlarm(context, time)
    }

}