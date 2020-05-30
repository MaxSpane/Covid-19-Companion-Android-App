package it.weMake.covid19Companion.di.modules.services

import dagger.Module
import dagger.android.ContributesAndroidInjector
import it.weMake.covid19Companion.di.PerService
import it.weMake.covid19Companion.services.DownloadManagerIntentService
import it.weMake.covid19Companion.services.MyFirebaseMessagingService

@Module
abstract class ServiceModule{

    @PerService
    @ContributesAndroidInjector
    internal abstract fun bindDownloadManagerIntentService(): DownloadManagerIntentService

    @PerService
    @ContributesAndroidInjector
    internal abstract fun bindMyFirebaseMessagingService(): MyFirebaseMessagingService

}