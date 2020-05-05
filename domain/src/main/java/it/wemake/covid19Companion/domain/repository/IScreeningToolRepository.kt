package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.screeningTool.DiagnosisDomainModel
import it.wemake.covid19Companion.domain.models.screeningTool.NextQuestionDomainModel
import it.wemake.covid19Companion.domain.models.screeningTool.requests.ScreeningToolRequestDomainModel
import kotlinx.coroutines.flow.Flow

interface IScreeningToolRepository {

    suspend fun requestNextQuestion(request: ScreeningToolRequestDomainModel): Flow<NextQuestionDomainModel>

    suspend fun getDiagnosis(request: ScreeningToolRequestDomainModel): Flow<DiagnosisDomainModel>

}