package it.wemake.covid19Companion.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.wemake.covid19Companion.local.models.AppReleaseLocalModel
import it.wemake.covid19Companion.local.models.CountryLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AppReleasesDao {

    @Query("SELECT * FROM app_releases ORDER BY versionName DESC")
    fun getAllAppReleases(): Flow<List<AppReleaseLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppRelease(appRelease: AppReleaseLocalModel)

}