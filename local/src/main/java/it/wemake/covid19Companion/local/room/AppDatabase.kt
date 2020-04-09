package it.wemake.covid19Companion.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import it.wemake.covid19Companion.local.models.AreaCasesDataLocalModel
import it.wemake.covid19Companion.local.room.dao.AreasCasesDataDao
import it.wemake.covid19Companion.local.utils.DB_NAME

@Database(entities = [AreaCasesDataLocalModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getCountryCasesDao(): AreasCasesDataDao

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
        ).build()
    }

}