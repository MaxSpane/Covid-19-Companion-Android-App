package it.wemake.covid19Companion.remote.api

import it.wemake.covid19Companion.remote.models.casesData.AllAreasCasesDataRemoteModel
import retrofit2.http.GET

interface BingCovid19ApiService {

    @GET("data")
    suspend fun getCasesData(): AllAreasCasesDataRemoteModel

}