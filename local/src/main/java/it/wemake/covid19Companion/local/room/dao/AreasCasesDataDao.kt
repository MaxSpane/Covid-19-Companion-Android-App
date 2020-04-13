package it.wemake.covid19Companion.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.wemake.covid19Companion.local.models.AreaCasesDataLocalModel
import it.wemake.covid19Companion.local.models.CountriesCasesDataLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AreasCasesDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAreasCasesData(areaCaseData: List<AreaCasesDataLocalModel>)

    @Query("SELECT * FROM area_cases_data WHERE id = 'world'")
    fun getGlobalCasesData(): Flow<AreaCasesDataLocalModel?>

    @Query("SELECT * FROM area_cases_data WHERE parentId = :parentId")
    fun getAreasCasesData(parentId: String): Flow<List<AreaCasesDataLocalModel>>
//    LEFT JOIN countries ON area_cases_data.displayName = countries.name , countries.iso2

    @Query("SELECT area_cases_data.id, area_cases_data.displayName, area_cases_data.totalDeaths, area_cases_data.totalRecovered, area_cases_data.totalConfirmed, area_cases_data.totalConfirmedDelta, area_cases_data.totalDeathsDelta, area_cases_data.totalRecoveredDelta, area_cases_data.hasAreasData, countries.iso2 FROM area_cases_data" +
            " LEFT JOIN" +
            " countries" +
            " ON" +
            " area_cases_data.displayName = countries.name WHERE parentId = 'world'")
    fun getCountriesCasesData(): Flow<List<CountriesCasesDataLocalModel>>

}