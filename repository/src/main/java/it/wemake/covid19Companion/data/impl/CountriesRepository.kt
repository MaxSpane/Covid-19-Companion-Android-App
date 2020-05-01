package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.ICountriesLocal
import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.domain.models.CountryDomainModel
import it.wemake.covid19Companion.domain.repository.ICountriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CountriesRepository
//@Inject constructor(
//    private val countriesLocal: ICountriesLocal
//): ICountriesRepository {
//
//    override suspend fun getCountries(): Flow<List<CountryDomainModel>> =
//        countriesLocal.getCountries().map { countries->
//            countries.map {
//                it.toDomain()
//            }
//        }
//
//}