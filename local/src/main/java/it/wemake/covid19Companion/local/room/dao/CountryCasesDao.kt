package it.wemake.covid19Companion.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.wemake.covid19Companion.local.models.CasesStatsLocal
import it.wemake.covid19Companion.local.models.CountryCasesLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryCasesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(countryCases: List<CountryCasesLocalModel>)

    @Query("SELECT * FROM country_cases")
    fun getAllCountries(): Flow<List<CountryCasesLocalModel>>

    @Query("SELECT sum(totalConfirmed) as allConfirmedCases, sum(totalRecovered) as allRecovered, sum(totalDeaths) as allDeaths FROM country_cases")
    fun getStats(): Flow<CasesStatsLocal>

}