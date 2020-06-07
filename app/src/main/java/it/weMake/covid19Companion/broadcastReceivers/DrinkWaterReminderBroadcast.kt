package it.weMake.covid19Companion.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.android.DaggerBroadcastReceiver
import it.weMake.covid19Companion.R
import it.weMake.covid19Companion.utils.*
import it.wemake.covid19Companion.domain.usecases.GetDrinkWaterIntervalUseCase
import it.wemake.covid19Companion.domain.usecases.GetUseCustomNotificationToneUseCase
import it.wemake.covid19Companion.domain.usecases.GetUsernameUseCase
import javax.inject.Inject

class DrinkWaterReminderBroadcast : DaggerBroadcastReceiver(){

    @Inject
    lateinit var getDrinkWaterIntervalUseCase: GetDrinkWaterIntervalUseCase

    @Inject
    lateinit var getUseCustomNotificationToneUseCase: GetUseCustomNotificationToneUseCase

    @Inject
    lateinit var getUsernameUseCase: GetUsernameUseCase

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        showReminderNotification(
            context,
            DRINK_WATER_NOTIFICATION_ID,
            context.getString(R.string.text_drink_water_reminder),
            getUseCustomNotificationToneUseCase(),
            getUsernameUseCase()
        )

        val time = minutesToMilliSecs(getDrinkWaterIntervalUseCase())
        createCancelDrinkWaterAlarm(context, time)
    }

}