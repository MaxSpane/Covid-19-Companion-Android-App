package it.wemake.covid19Companion.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.wemake.covid19Companion.local.models.CountryLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countries: List<CountryLocalModel>)

    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<CountryLocalModel>

}