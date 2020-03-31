package it.wemake.covid19Companion.data.local

import it.wemake.covid19Companion.data.models.CountryEntityModel
import kotlinx.coroutines.flow.Flow

interface ICountryLocal {

    suspend fun insertCountries(countries: List<CountryEntityModel>)

    suspend fun getCountries(): Flow<List<CountryEntityModel>>

}