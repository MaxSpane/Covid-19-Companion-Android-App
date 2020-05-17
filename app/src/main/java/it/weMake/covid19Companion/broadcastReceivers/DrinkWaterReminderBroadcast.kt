package it.weMake.covid19Companion.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.android.DaggerBroadcastReceiver
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.utils.*
import it.wemake.covid19Companion.domain.usecases.GetDrinkWaterIntervalUseCase
import javax.inject.Inject

class DrinkWaterReminderBroadcast : DaggerBroadcastReceiver(){

    @Inject
    lateinit var getDrinkWaterIntervalUseCase: GetDrinkWaterIntervalUseCase

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        showReminderNotification(
            context,
            DRINK_WATER_NOTIFICATION_CHANNEL_ID,
            DRINK_WATER_NOTIFICATION_ID,
            context.getString(R.string.text_drink_water_reminder))

        val time = minutesToMilliSecs(getDrinkWaterIntervalUseCase())
        createCancelDrinkWaterAlarm(context, time)
    }

}