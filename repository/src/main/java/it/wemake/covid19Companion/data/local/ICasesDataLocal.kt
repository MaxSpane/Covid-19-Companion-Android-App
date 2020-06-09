package it.wemake.covid19Companion.data.local

import it.wemake.covid19Companion.data.models.casesData.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.casesData.GlobalStatsEntity
import it.wemake.covid19Companion.data.models.casesData.RegionCasesDataEntity
import kotlinx.coroutines.flow.Flow

interface ICasesDataLocal {

    suspend fun insertCountriesCasesData(countryCasesData: List<CountryCasesDataEntity>)

    suspend fun getPagedCountriesCasesData(page: Int, pageSize: Int, sortBy: String): Flow<List<CountryCasesDataEntity>>

    suspend fun getGlobalCasesData(): Flow<GlobalStatsEntity>

    suspend fun searchCountriesCasesData(searchQuery: String, page: Int, pageSize: Int): Flow<List<CountryCasesDataEntity>>

    suspend fun getUserCountryCasesData(userCountryIso2: String): Flow<CountryCasesDataEntity?>

    suspend fun getAllCountryRegionsCasesData(countryName: String, sortBy: String): Flow<List<RegionCasesDataEntity>>

    suspend fun insertRegionsCasesData(regionsCasesData: List<RegionCasesDataEntity>)

    suspend fun getCountryRegionsCasesDataLatestUpdatedDate(countryName: String): Long?

    suspend fun getAllCountriesCasesData(sortBy: String): Flow<List<CountryCasesDataEntity>>

}