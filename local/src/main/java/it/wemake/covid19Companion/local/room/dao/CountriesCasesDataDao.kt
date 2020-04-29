package it.wemake.covid19Companion.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.wemake.covid19Companion.local.models.CountryCasesDataLocalModel
import it.wemake.covid19Companion.local.models.GlobalStatsLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesCasesDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAreasCasesData(areaCaseData: List<CountryCasesDataLocalModel>)

    @Query("SELECT SUM(totalConfirmed) as confirmed, SUM(totalDeaths) as deaths, SUM(totalRecovered) as recovered FROM countries_cases_data")
    fun getGlobalCasesData(): Flow<GlobalStatsLocalModel>

    @Query("SELECT * FROM countries_cases_data ORDER BY totalConfirmed DESC LIMIT :pageSize OFFSET (:page * 10)")
    fun getCountriesCasesData(page: Int, pageSize: Int): Flow<List<CountryCasesDataLocalModel>>

    @Query("SELECT * FROM countries_cases_data WHERE displayName LIKE :searchQuery ORDER BY totalConfirmed DESC LIMIT :pageSize OFFSET (:page * 10)")
    fun searchCountriesCasesData(searchQuery: String, page: Int, pageSize: Int): Flow<List<CountryCasesDataLocalModel>>

}