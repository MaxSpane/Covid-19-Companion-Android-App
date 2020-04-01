package it.wemake.covid19Companion.data.local

import it.wemake.covid19Companion.data.models.CasesStatsEntity
import it.wemake.covid19Companion.data.models.CountryCasesEntity
import kotlinx.coroutines.flow.Flow

interface ICountryCasesLocal {

    suspend fun insertCountries(countries: List<CountryCasesEntity>)

    suspend fun getCountries(): Flow<List<CountryCasesEntity>>

    suspend fun getCasesStats(): Flow<CasesStatsEntity>

}