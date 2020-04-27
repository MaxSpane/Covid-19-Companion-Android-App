package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.AreaCasesDataDomainModel
import it.wemake.covid19Companion.domain.models.CountryCasesDomainModel
import kotlinx.coroutines.flow.Flow

interface ICovid19CasesRepository {

    suspend fun updateCasesData()

    suspend fun getAreasCasesData(parentId: String): Flow<List<AreaCasesDataDomainModel>>

    suspend fun getGlobalCasesData(): Flow<AreaCasesDataDomainModel?>

    suspend fun getCountriesCasesData(page: Int, pageSize: Int): Flow<List<CountryCasesDomainModel>>

}