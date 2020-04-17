package it.weMake.covid19Companion.di.modules.local

import android.content.Context
import dagger.Module
import dagger.Provides
import it.wemake.covid19Companion.local.room.AppDatabase
import it.wemake.covid19Companion.local.room.dao.AreasCasesDataDao
import it.wemake.covid19Companion.local.room.dao.CountriesDao
import javax.inject.Named

@Module
class RoomModule {

    @Provides
    fun provideDatabase(@Named("AppContext") application: Context) = AppDatabase.invoke(application)

    @Provides
    fun provideAreasCasesDataDao(database: AppDatabase): AreasCasesDataDao =
        database.getAreasCasesDataDao()

    @Provides
    fun provideCountriesDao(database: AppDatabase): CountriesDao =
        database.getCountriesDao()

}