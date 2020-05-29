package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.casesData.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.models.casesData.GlobalStatsDomainModel
import it.wemake.covid19Companion.domain.models.casesData.RegionCasesDataDomainModel
import kotlinx.coroutines.flow.Flow

interface ICovid19CasesRepository {

    suspend fun updateCasesData()

    suspend fun getGlobalCasesData(): Flow<GlobalStatsDomainModel>

    suspend fun getCountriesCasesData(page: Int, pageSize: Int, sortBy: String): Flow<List<CountryCasesDomainModel>>

    suspend fun searchCountriesCasesData(searchQuery: String, page: Int, pageSize: Int): Flow<List<CountryCasesDomainModel>>

    suspend fun getUserCountryCasesData(): Flow<CountryCasesDomainModel?>

    suspend fun updateCountryRegionsCasesData(countryName: String)

    suspend fun getAllCountryRegionsCasesData(countryName: String, sortBy: String): Flow<List<RegionCasesDataDomainModel>>

}