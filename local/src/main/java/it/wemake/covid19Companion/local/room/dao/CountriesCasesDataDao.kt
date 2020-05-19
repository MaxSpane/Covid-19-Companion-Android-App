package it.wemake.covid19Companion.local.room.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import it.wemake.covid19Companion.local.models.CountryCasesDataLocalModel
import it.wemake.covid19Companion.local.models.GlobalStatsLocalModel
import kotlinx.coroutines.flow.Flow


@Dao
abstract class CountriesCasesDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAreasCasesData(areaCaseData: List<CountryCasesDataLocalModel>)

    @Query("SELECT SUM(totalConfirmed) as confirmed, SUM(totalDeaths) as deaths, SUM(totalRecovered) as recovered FROM countries_cases_data")
    abstract fun getGlobalCasesData(): Flow<GlobalStatsLocalModel>

    @Query("SELECT * FROM countries_cases_data ORDER BY totalConfirmed DESC LIMIT :pageSize OFFSET (:page * 10)")
    abstract fun getCountriesCasesData(page: Int, pageSize: Int): Flow<List<CountryCasesDataLocalModel>>

    @Query("SELECT * FROM countries_cases_data WHERE displayName LIKE :searchQuery ORDER BY totalConfirmed DESC LIMIT :pageSize OFFSET (:page * 10)")
    abstract fun searchCountriesCasesData(searchQuery: String, page: Int, pageSize: Int): Flow<List<CountryCasesDataLocalModel>>

    @Query("SELECT * FROM countries_cases_data WHERE iso2 = :userCountryIso2")
    abstract fun getUserCountryCasesData(userCountryIso2: String): Flow<CountryCasesDataLocalModel?>

    @RawQuery(observedEntities = [CountryCasesDataLocalModel::class])
    abstract fun getCountriesCasesDataViaRawQuery(query: SupportSQLiteQuery?): Flow<List<CountryCasesDataLocalModel>>

    fun getPagedCountriesCasesDataOrderBy(page: Int, pageSize: Int, column: String): Flow<List<CountryCasesDataLocalModel>> {
        val statement = "SELECT * FROM countries_cases_data ORDER BY $column DESC LIMIT $pageSize OFFSET ($page * 10)"
        val query: SupportSQLiteQuery = SimpleSQLiteQuery(statement, arrayOf())
        return getCountriesCasesDataViaRawQuery(query)
    }

    fun getAllCountriesCasesDataOrderBy(column: String): Flow<List<CountryCasesDataLocalModel>> {
        val statement = "SELECT * FROM countries_cases_data ORDER BY $column"
        val query: SupportSQLiteQuery = SimpleSQLiteQuery(statement, arrayOf())
        return getCountriesCasesDataViaRawQuery(query)
    }

}