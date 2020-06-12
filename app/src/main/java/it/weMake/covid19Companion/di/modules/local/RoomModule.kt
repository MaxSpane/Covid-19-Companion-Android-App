package it.weMake.covid19Companion.di.modules.local

import android.content.Context
import dagger.Module
import dagger.Provides
import it.wemake.covid19Companion.local.room.AppDatabase
import it.wemake.covid19Companion.local.room.dao.*
import javax.inject.Named

@Module
class RoomModule {

    @Provides
    fun provideDatabase(@Named("AppContext") application: Context) = AppDatabase.invoke(application)

    @Provides
    fun provideCountriesCasesDataDao(database: AppDatabase): CountriesCasesDataDao =
        database.getCountriesCasesDataDao()

    @Provides
    fun providePreventionTipsDao(database: AppDatabase): PreventionTipsDao =
        database.getPreventionTipsDao()

    @Provides
    fun provideCountriesDao(database: AppDatabase): CountriesDao =
        database.getCountriesDao()

    @Provides
    fun provideWashHandsReminderLocationsDao(database: AppDatabase): WashHandsReminderLocationsDao =
        database.getWashHandsReminderLocationsDao()

    @Provides
    fun provideRegionsCasesDataDao(database: AppDatabase): RegionsCasesDataDao =
        database.getRegionsCasesDataDao()

    @Provides
    fun provideAppReleasesDao(database: AppDatabase): AppReleasesDao =
        database.getAppReleasesDao()

    @Provides
    fun provideSourcesDao(database: AppDatabase): SourcesDao =
        database.getSourcesDao()

    @Provides
    fun provideDevelopmentTeamDao(database: AppDatabase): DevelopmentTeamDao =
        database.getDevelopmentTeamDao()

}