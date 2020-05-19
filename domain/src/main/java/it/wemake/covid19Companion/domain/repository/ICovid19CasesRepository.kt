package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.casesData.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.models.casesData.GlobalStatsDomainModel
import kotlinx.coroutines.flow.Flow

interface ICovid19CasesRepository {

    suspend fun updateCasesData()

    suspend fun getGlobalCasesData(): Flow<GlobalStatsDomainModel>

    suspend fun getPagedCountriesCasesData(page: Int, pageSize: Int, sortBy: String): Flow<List<CountryCasesDomainModel>>

    suspend fun searchCountriesCasesData(searchQuery: String, page: Int, pageSize: Int): Flow<List<CountryCasesDomainModel>>

    suspend fun getUserCountryCasesData(): Flow<CountryCasesDomainModel?>

    suspend fun getAllCountriesCasesData(sortBy: String): Flow<List<CountryCasesDomainModel>>

}