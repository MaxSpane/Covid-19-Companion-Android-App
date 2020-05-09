package it.wemake.covid19Companion.remote.mappers

import it.wemake.covid19Companion.data.models.screeningTool.ChoiceEntity
import it.wemake.covid19Companion.data.models.screeningTool.NextQuestionEntity
import it.wemake.covid19Companion.data.models.screeningTool.QuestionEntity
import it.wemake.covid19Companion.data.models.screeningTool.QuestionItemEntity
import it.wemake.covid19Companion.data.models.screeningTool.requests.EvidenceEntity
import it.wemake.covid19Companion.data.models.screeningTool.requests.ScreeningToolRequestEntity
import it.wemake.covid19Companion.remote.models.screeningTool.ChoiceRemoteModel
import it.wemake.covid19Companion.remote.models.screeningTool.NextQuestionRemoteModel
import it.wemake.covid19Companion.remote.models.screeningTool.QuestionItemRemoteModel
import it.wemake.covid19Companion.remote.models.screeningTool.QuestionRemoteModel
import it.wemake.covid19Companion.remote.models.screeningTool.request.EvidenceRemoteModel
import it.wemake.covid19Companion.remote.models.screeningTool.request.ScreeningToolRequestRemoteModel

internal fun ScreeningToolRequestEntity.toRemote(): ScreeningToolRequestRemoteModel =
    ScreeningToolRequestRemoteModel(
        sex,
        age,
        evidence.map { it.toRemote() }
    )

internal fun EvidenceEntity.toRemote(): EvidenceRemoteModel =
    EvidenceRemoteModel(
        id,
        choiceId
    )