package it.wemake.covid19Companion.data.mappers

import it.wemake.covid19Companion.data.models.screeningTool.requests.EvidenceEntity
import it.wemake.covid19Companion.data.models.screeningTool.requests.ScreeningToolRequestEntity
import it.wemake.covid19Companion.domain.models.screeningTool.requests.EvidenceDomainModel
import it.wemake.covid19Companion.domain.models.screeningTool.requests.ScreeningToolRequestDomainModel

internal fun ScreeningToolRequestDomainModel.toEntity(): ScreeningToolRequestEntity =
    ScreeningToolRequestEntity(
        sex,
        age,
        evidence.map { it.toEntity() }
    )

internal fun EvidenceDomainModel.toEntity(): EvidenceEntity =
    EvidenceEntity(
        id,
        choiceId
    )