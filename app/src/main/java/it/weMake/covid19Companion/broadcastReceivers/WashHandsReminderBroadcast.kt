package it.weMake.covid19Companion.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import it.weMake.covid19Companion.utils.*

class WashHandsReminderBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        showWashHandsReminderNotification(context)
        val time = intent.getLongExtra(EXTRA_ALARM_INTERVAL, 0)
        createCancelWashHandsAlarm(context, time)
    }

}