package it.weMake.covid19Companion.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import it.weMake.covid19Companion.utils.*

class DrinkWaterReminderBroadcast : BroadcastReceiver(){

    override fun onReceive(context: Context, intent: Intent) {
        showDrinkWaterReminderNotification(context)
        val time = intent.getLongExtra(EXTRA_ALARM_INTERVAL, 0)
        createCancelDrinkWaterAlarm(context, time)
    }

}