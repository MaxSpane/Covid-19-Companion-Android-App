package it.wemake.covid19Companion.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import it.wemake.covid19Companion.local.models.CountryCasesDataLocalModel
import it.wemake.covid19Companion.local.models.CountryLocalModel
import it.wemake.covid19Companion.local.models.PreventionTipLocalModel
import it.wemake.covid19Companion.local.models.WashHandsReminderLocationLocalModel
import it.wemake.covid19Companion.local.models.RegionCasesDataLocalModel
import it.wemake.covid19Companion.local.room.dao.CountriesCasesDataDao
import it.wemake.covid19Companion.local.room.dao.CountriesDao
import it.wemake.covid19Companion.local.room.dao.PreventionTipsDao
import it.wemake.covid19Companion.local.room.dao.WashHandsReminderLocationsDao
import it.wemake.covid19Companion.local.room.dao.RegionsCasesDataDao
import it.wemake.covid19Companion.local.utils.DB_NAME

@Database(
    entities = [
        CountryCasesDataLocalModel::class,
        CountryLocalModel::class,
        PreventionTipLocalModel::class,
        WashHandsReminderLocationLocalModel::class,
        RegionCasesDataLocalModel::class
    ],
    version = 1,
    exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getCountriesCasesDataDao(): CountriesCasesDataDao
    abstract fun getPreventionTipsDao(): PreventionTipsDao
    abstract fun getCountriesDao(): CountriesDao
    abstract fun getWashHandsReminderLocationsDao(): WashHandsReminderLocationsDao
    abstract fun getRegionsCasesDataDao(): RegionsCasesDataDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, DB_NAME
        )
            .createFromAsset("databases/covid_19_companion.db")
            .build()
    }

}