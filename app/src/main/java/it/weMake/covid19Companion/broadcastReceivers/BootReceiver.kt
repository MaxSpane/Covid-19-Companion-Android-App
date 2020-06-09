package it.weMake.covid19Companion.broadcastReceivers

import android.content.Context
import android.content.Intent
import dagger.android.DaggerBroadcastReceiver
import it.weMake.covid19Companion.utils.createCancelDrinkWaterAlarm
import it.weMake.covid19Companion.utils.createCancelWashHandsAlarm
import it.weMake.covid19Companion.utils.minutesToMilliSecs
import it.wemake.covid19Companion.domain.usecases.GetDrinkWaterIntervalUseCase
import it.wemake.covid19Companion.domain.usecases.GetWashHandsIntervalUseCase
import javax.inject.Inject

class BootReceiver: DaggerBroadcastReceiver() {

    @Inject
    lateinit var getDrinkWaterIntervalUseCase: GetDrinkWaterIntervalUseCase

    @Inject
    lateinit var getWashHandsIntervalUseCase: GetWashHandsIntervalUseCase

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        val washHandsInterval = getWashHandsIntervalUseCase()
        val drinkWaterInterval = getDrinkWaterIntervalUseCase()

        if (washHandsInterval != 0){
            createCancelWashHandsAlarm(context, minutesToMilliSecs(washHandsInterval))
        }

        if (drinkWaterInterval != 0){
            createCancelDrinkWaterAlarm(context, minutesToMilliSecs(drinkWaterInterval))
        }

    }

}