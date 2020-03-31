package it.weMake.covid19Companion.di.modules.local

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import it.wemake.covid19Companion.local.room.AppDatabase
import it.wemake.covid19Companion.local.room.dao.CountryDao
import javax.inject.Named

@Module
class RoomModule {

    @Provides
    fun provideDatabase(@Named("AppContext") application: Context) = AppDatabase.invoke(application)

    @Provides
    fun provideCountryDao(database: AppDatabase): CountryDao =
        database.getCountryDao()

}