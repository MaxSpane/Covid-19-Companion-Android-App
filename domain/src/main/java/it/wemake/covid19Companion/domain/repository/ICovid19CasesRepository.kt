package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.AreaCasesDataDomainModel
import it.wemake.covid19Companion.domain.models.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.models.GlobalStatsDomainModel
import kotlinx.coroutines.flow.Flow

interface ICovid19CasesRepository {

    suspend fun updateCasesData()

    suspend fun getGlobalCasesData(): Flow<GlobalStatsDomainModel>

    suspend fun getCountriesCasesData(page: Int, pageSize: Int): Flow<List<CountryCasesDomainModel>>

}