package it.wemake.covid19Companion.data.remote

import it.wemake.covid19Companion.data.models.screeningTool.DiagnosisEntity
import it.wemake.covid19Companion.data.models.screeningTool.NextQuestionEntity
import it.wemake.covid19Companion.data.models.screeningTool.requests.ScreeningToolRequestEntity
import kotlinx.coroutines.flow.Flow

interface IScreeningToolRemote {

    suspend fun requestNextQuestion(request: ScreeningToolRequestEntity): Flow<NextQuestionEntity>

    suspend fun getDiagnosis(request: ScreeningToolRequestEntity): Flow<DiagnosisEntity>

}