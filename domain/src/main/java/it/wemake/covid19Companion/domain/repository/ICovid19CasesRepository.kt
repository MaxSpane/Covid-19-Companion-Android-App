package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.CountryDomainModel
import kotlinx.coroutines.flow.Flow

interface ICovid19CasesRepository {

    suspend fun insertCountries(countries: List<CountryDomainModel>)

    suspend fun getCountries(): Flow<List<CountryDomainModel>>

}