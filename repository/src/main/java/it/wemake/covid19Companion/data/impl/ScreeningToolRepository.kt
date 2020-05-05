package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.data.mappers.toEntity
import it.wemake.covid19Companion.data.remote.IScreeningToolRemote
import it.wemake.covid19Companion.domain.models.screeningTool.DiagnosisDomainModel
import it.wemake.covid19Companion.domain.models.screeningTool.NextQuestionDomainModel
import it.wemake.covid19Companion.domain.models.screeningTool.requests.ScreeningToolRequestDomainModel
import it.wemake.covid19Companion.domain.repository.IScreeningToolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScreeningToolRepository @Inject constructor(
    private val screeningToolRemote: IScreeningToolRemote
): IScreeningToolRepository {

    override suspend fun requestNextQuestion(request: ScreeningToolRequestDomainModel): Flow<NextQuestionDomainModel> =
        screeningToolRemote.requestNextQuestion(request.toEntity()).map { it.toDomain() }


    override suspend fun getDiagnosis(request: ScreeningToolRequestDomainModel): Flow<DiagnosisDomainModel> =
        screeningToolRemote.getDiagnosis(request.toEntity()).map { it.toDomain() }

}