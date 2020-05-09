package it.weMake.covid19Companion.mappers

import it.weMake.covid19Companion.models.screeningTool.request.Evidence
import it.weMake.covid19Companion.models.screeningTool.request.ScreeningToolRequest
import it.wemake.covid19Companion.domain.models.screeningTool.requests.EvidenceDomainModel
import it.wemake.covid19Companion.domain.models.screeningTool.requests.ScreeningToolRequestDomainModel

fun ScreeningToolRequest.toDomain(): ScreeningToolRequestDomainModel =
    ScreeningToolRequestDomainModel(
        sex,
        age,
        evidence.map { it.toDomain() }
    )

fun Evidence.toDomain(): EvidenceDomainModel =
    EvidenceDomainModel(
        id,
        choiceId
    )