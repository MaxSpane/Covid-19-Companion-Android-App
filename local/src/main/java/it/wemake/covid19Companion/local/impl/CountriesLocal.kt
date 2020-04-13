package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.ICountriesLocal
import it.wemake.covid19Companion.data.models.CountryEntityModel
import it.wemake.covid19Companion.local.mappers.toEntity
import it.wemake.covid19Companion.local.room.dao.CountriesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CountriesLocal @Inject constructor(
    private val countriesDao: CountriesDao
): ICountriesLocal {

    override suspend fun getCountries(): Flow<List<CountryEntityModel>> =
        countriesDao.getAllCountries().map {countries->
            countries.map {
                it.toEntity()
            }
        }

}