package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.ICountryLocal
import it.wemake.covid19Companion.data.models.CountryEntityModel
import it.wemake.covid19Companion.local.mappers.toEntity
import it.wemake.covid19Companion.local.mappers.toLocal
import it.wemake.covid19Companion.local.room.dao.CountryDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryLocal @Inject constructor(
    private val countryDao: CountryDao
): ICountryLocal{
    override suspend fun insertCountries(countries: List<CountryEntityModel>) {
        countryDao.insertCountries(countries.map {
            it.toLocal()
        })
    }

    override suspend fun getCountries(): Flow<List<CountryEntityModel>> =
        flow { emit(countryDao.getAllCountries().map {
            it.toEntity()
        }) }
}