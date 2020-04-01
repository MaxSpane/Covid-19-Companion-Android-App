package it.wemake.covid19Companion.remote.api

import it.wemake.covid19Companion.remote.models.casesSummary.CasesSummaryRemoteModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface Covid19ApiService {

    @GET("summary")
    suspend fun getCasesSummary(): CasesSummaryRemoteModel

}