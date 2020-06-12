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
        SourceLocalModel::class,
        TeamMemberLocalModel::class
    ],
    version = 3,
    exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getCountriesCasesDataDao(): CountriesCasesDataDao
    abstract fun getPreventionTipsDao(): PreventionTipsDao
    abstract fun getCountriesDao(): CountriesDao
    abstract fun getWashHandsReminderLocationsDao(): WashHandsReminderLocationsDao
    abstract fun getRegionsCasesDataDao(): RegionsCasesDataDao
    abstract fun getAppReleasesDao(): AppReleasesDao
    abstract fun getSourcesDao(): SourcesDao
    abstract fun getDevelopmentTeamDao(): DevelopmentTeamDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("UPDATE `prevention_tips` SET `preventionTip` = 'Maintain at least 2 metre (6 feet) distance between yourself and anyone who is coughing or sneezing.' WHERE `iconId` = 'maintain_social_distancing'")
                database.execSQL("UPDATE `prevention_tips` SET `preventionTip` = 'Avoid crowded places, especially if you are over 60 or have an underlying health condition such as high blood pressure, diabetes, heart and lung diseases or cancer.' WHERE `iconId` = 'avoid_crowded_places'")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `development_team` (`name` TEXT NOT NULL, `role` TEXT NOT NULL, `about` TEXT NOT NULL, `externalLink` TEXT NOT NULL, PRIMARY KEY(`name`))")
                database.execSQL("INSERT OR IGNORE INTO `development_team` (`name`, `role`, `about`, `externalLink`) VALUES ('Richard Saseun', 'UI, UX Designer', 'A graphics guru with a penchant for coding; who loves solving problems and creating products to make people lives easier.', 'http://richardsaseun.com'), ('Mohammed Adetunji', 'Native Mobile App Developer', 'A skilled Mobile App sculptor with a love for Technology; out to solve the Worlds problems one App at a time.', 'https://github.com/MaxSpane'), ('David Idowu', 'Native Android Developer', 'A versed programmer; trying to learn the language of the future.', 'https://github.com/Marshall-D')")
                database.execSQL("INSERT OR IGNORE INTO `app_releases` (`versionName`, `versionDetails`) VALUES ('1.1', 'Added development team to about\nAdded about app text\nUpdated about page design\nShowing icon for countries whose regional data are not available\nShowing toast when country card with unavailable regional data is clicked.')")
            }
        }

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
            .addMigrations(MIGRATION_1_2)
            .addMigrations(MIGRATION_2_3)
            .build()
    }

}