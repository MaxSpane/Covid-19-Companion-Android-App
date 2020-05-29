package it.wemake.covid19Companion.remote.api

import it.wemake.covid19Companion.remote.models.casesData.CountryCasesDataRemoteModel
import it.wemake.covid19Companion.remote.models.casesData.NigeriaRegionCasesDataRemoteModel
import retrofit2.http.GET

interface NovelCovidApiService {

    @GET("v2/countries?allowNull=true")
    suspend fun getCountriesCasesData(): List<CountryCasesDataRemoteModel>

    @GET("v2/gov/nigeria")
    suspend fun getNigeriaRegionsCasesData(): List<NigeriaRegionCasesDataRemoteModel>

}