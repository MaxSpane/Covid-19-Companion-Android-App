package it.wemake.covid19Companion.remote.api

import it.wemake.covid19Companion.data.models.screeningTool.requests.ScreeningToolRequestEntity
import it.wemake.covid19Companion.remote.models.screeningTool.DiagnosisRemoteModel
import it.wemake.covid19Companion.remote.models.screeningTool.NextQuestionRemoteModel
import it.wemake.covid19Companion.remote.models.screeningTool.request.ScreeningToolRequestRemoteModel
import it.wemake.covid19Companion.remote.utils.HEADER_APP_ID
import it.wemake.covid19Companion.remote.utils.HEADER_APP_KEY
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface InfermedicaApiService {

    @POST("diagnosis")
    suspend fun requestNextQuestion(
        @Header(HEADER_APP_ID) appId: String,
        @Header(HEADER_APP_KEY) appKey: String,
        @Body request: ScreeningToolRequestRemoteModel
    ): NextQuestionRemoteModel

    @POST("triage")
    suspend fun getDiagnosis(
        @Header(HEADER_APP_ID) appId: String,
        @Header(HEADER_APP_KEY) appKey: String,
        @Body request: ScreeningToolRequestRemoteModel
    ): DiagnosisRemoteModel

}