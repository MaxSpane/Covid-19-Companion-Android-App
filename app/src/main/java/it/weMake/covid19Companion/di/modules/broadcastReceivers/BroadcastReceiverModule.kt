package it.weMake.covid19Companion.di.modules.broadcastReceivers

import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.weMake.covid19Companion.broadcastReceivers.DrinkWaterReminderBroadcast
import it.weMake.covid19Companion.broadcastReceivers.GeofenceBroadcastReceiver
import it.weMake.covid19Companion.broadcastReceivers.WashHandsReminderBroadcast
import it.weMake.covid19Companion.di.PerBroadcastReceiver
import it.weMake.covid19Companion.services.DownloadManagerIntentService

@Module
abstract class BroadcastReceiverModule{

    @PerBroadcastReceiver
    @ContributesAndroidInjector
    internal abstract fun bindWashHandsReminderBroadcast(): WashHandsReminderBroadcast

    @PerBroadcastReceiver
    @ContributesAndroidInjector
    internal abstract fun bindDrinkWaterReminderBroadcast(): DrinkWaterReminderBroadcast

    @PerBroadcastReceiver
    @ContributesAndroidInjector
    internal abstract fun bindGeofenceBroadcastReceiver(): GeofenceBroadcastReceiver

}