package it.wemake.covid19Companion.local.room.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import it.wemake.covid19Companion.local.models.CountryCasesDataLocalModel
import it.wemake.covid19Companion.local.models.GlobalStatsLocalModel
import it.wemake.covid19Companion.local.models.RegionCasesDataLocalModel
import kotlinx.coroutines.flow.Flow


@Dao
abstract class RegionsCasesDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertRegionsCasesData(regionsCasesData: List<RegionCasesDataLocalModel>)

    @RawQuery(observedEntities = [RegionCasesDataLocalModel::class])
    abstract fun getCountryRegionsCasesDataViaRawQuery(query: SupportSQLiteQuery?): Flow<List<RegionCasesDataLocalModel>>

    fun getCountriesCasesDataOrderBy(countryName: String, column: String): Flow<List<RegionCasesDataLocalModel>> {
        val statement = "SELECT * FROM region_cases_data WHERE parentCountryName = '$countryName' ORDER BY $column DESC"
        val query: SupportSQLiteQuery = SimpleSQLiteQuery(statement, arrayOf())
        return getCountryRegionsCasesDataViaRawQuery(query)
    }

    @Query("SELECT updated FROM region_cases_data WHERE parentCountryName = :countryName ORDER BY updated DESC")
    abstract suspend fun getCountryLatestUpdatedDate(countryName: String): Long?
}