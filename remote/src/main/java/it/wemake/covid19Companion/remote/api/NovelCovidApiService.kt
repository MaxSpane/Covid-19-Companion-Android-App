package it.wemake.covid19Companion.remote.api

import it.wemake.covid19Companion.remote.models.casesData.CountryCasesDataRemoteModel
import retrofit2.http.GET

interface NovelCovidApiService {

    @GET("v2/countries")
    suspend fun getCountriesCasesData(): List<CountryCasesDataRemoteModel>

}