package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.CasesStatsDomain
import it.wemake.covid19Companion.domain.models.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.models.CountryDomainModel
import kotlinx.coroutines.flow.Flow

interface ICovid19CasesRepository {

    suspend fun updateCasesSummary()

    suspend fun getCountriesCasesSummary(): Flow<List<CountryCasesDomainModel>>

    suspend fun getCasesStats(): Flow<CasesStatsDomain>

}