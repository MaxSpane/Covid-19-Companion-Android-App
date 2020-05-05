package it.wemake.covid19Companion.remote.impl

import it.wemake.covid19Companion.data.models.screeningTool.DiagnosisEntity
import it.wemake.covid19Companion.data.models.screeningTool.NextQuestionEntity
import it.wemake.covid19Companion.data.models.screeningTool.requests.ScreeningToolRequestEntity
import it.wemake.covid19Companion.data.remote.IScreeningToolRemote
import it.wemake.covid19Companion.remote.api.InfermedicaApiService
import it.wemake.covid19Companion.remote.mappers.toEntity
import it.wemake.covid19Companion.remote.mappers.toRemote
import it.wemake.covid19Companion.remote.utils.INFERMEDICA_APP_ID
import it.wemake.covid19Companion.remote.utils.INFERMEDICA_APP_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScreeningToolRemote @Inject constructor(
    private val infermedicaApiService: InfermedicaApiService
): IScreeningToolRemote {

    override suspend fun requestNextQuestion(request: ScreeningToolRequestEntity): Flow<NextQuestionEntity> =
        flow { emit(
            infermedicaApiService.requestNextQuestion(
                INFERMEDICA_APP_ID,
                INFERMEDICA_APP_KEY,
                request.toRemote()
            ).toEntity()
        )}

    override suspend fun getDiagnosis(request: ScreeningToolRequestEntity): Flow<DiagnosisEntity> =
        flow { emit(
            infermedicaApiService.getDiagnosis(
                INFERMEDICA_APP_ID,
                INFERMEDICA_APP_KEY,
                request.toRemote()
            ).toEntity()
        )}

}