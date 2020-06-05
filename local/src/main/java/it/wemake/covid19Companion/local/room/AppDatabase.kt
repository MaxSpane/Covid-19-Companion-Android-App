package it.wemake.covid19Companion.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import it.wemake.covid19Companion.local.models.*
import it.wemake.covid19Companion.local.room.dao.*
import it.wemake.covid19Companion.local.utils.DB_NAME

@Database(
    entities = [
        CountryCasesDataLocalModel::class,
        CountryLocalModel::class,
        PreventionTipLocalModel::class,
        WashHandsReminderLocationLocalModel::class,
        RegionCasesDataLocalModel::class,
        AppReleaseLocalModel::class,
        SourceLocalModel::class
    ],
    version = 1,
    exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getCountriesCasesDataDao(): CountriesCasesDataDao
    abstract fun getPreventionTipsDao(): PreventionTipsDao
    abstract fun getCountriesDao(): CountriesDao
    abstract fun getWashHandsReminderLocationsDao(): WashHandsReminderLocationsDao
    abstract fun getRegionsCasesDataDao(): RegionsCasesDataDao
    abstract fun getAppReleasesDao(): AppReleasesDao
    abstract fun getSourcesDao(): SourcesDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        //MOCK MIGRATION
//        val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("INSERT OR IGNORE INTO `prevention_tips` (`title`, `preventionTip`, `preventionTipWhy`, `iconId`) VALUES ('test2', 'tipp', 'whyy', '3')")
//            }
//        }

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