package it.wemake.covid19Companion.data.local

import it.wemake.covid19Companion.data.models.CountryEntityModel
import kotlinx.coroutines.flow.Flow

interface ICountriesLocal {

    suspend fun getCountries(): Flow<List<CountryEntityModel>>

}