package it.wemake.covid19Companion.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import it.wemake.covid19Companion.local.models.CountryLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesDao {

    @Query("SELECT * FROM countries")
    fun getAllCountries(): Flow<List<CountryLocalModel>>

}