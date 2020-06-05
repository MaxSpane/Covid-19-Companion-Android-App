package it.wemake.covid19Companion.data.mappers

import it.wemake.covid19Companion.data.models.appReleases.AppReleaseEntity
import it.wemake.covid19Companion.data.models.screeningTool.requests.EvidenceEntity
import it.wemake.covid19Companion.data.models.screeningTool.requests.ScreeningToolRequestEntity
import it.wemake.covid19Companion.domain.models.screeningTool.requests.EvidenceDomainModel
import it.wemake.covid19Companion.domain.models.screeningTool.requests.ScreeningToolRequestDomainModel
import it.wemake.covid19Companion.domain.models.washHandsReminderLocations.WashHandsReminderLocationDomainModel
import it.wemake.covid19Companion.data.models.washHandsReminderLocations.WashHandsReminderLocationEntity
import it.wemake.covid19Companion.domain.models.appReleases.AppReleaseDomainModel

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

internal fun WashHandsReminderLocationDomainModel.toEntity(): WashHandsReminderLocationEntity =
    WashHandsReminderLocationEntity(
        id,
        name,
        address,
        lat,
        lng,
        enabled
    )

internal fun AppReleaseDomainModel.toEntity(): AppReleaseEntity =
    AppReleaseEntity(
        versionName,
        versionDetails
    )