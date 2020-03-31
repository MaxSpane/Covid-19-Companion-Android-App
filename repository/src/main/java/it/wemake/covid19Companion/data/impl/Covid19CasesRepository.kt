package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.ICountryLocal
import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.data.mappers.toEntity
import it.wemake.covid19Companion.domain.models.CountryDomainModel
import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Covid19CasesRepository @Inject constructor(
    private val countryLocal: ICountryLocal
): ICovid19CasesRepository {
    override suspend fun insertCountries(countries: List<CountryDomainModel>) =
        countryLocal.insertCountries(countries.map {
            it.toEntity()
        })

    override suspend fun getCountries(): Flow<List<CountryDomainModel>> =
        countryLocal.getCountries().map {resp ->
            resp.map {
                it.toDomain()
            }
        }
}