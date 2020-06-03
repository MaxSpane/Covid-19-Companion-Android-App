package it.weMake.covid19Companion.mappers

import it.weMake.covid19Companion.models.appReleases.AppRelease
import it.weMake.covid19Companion.models.screeningTool.request.Evidence
import it.weMake.covid19Companion.models.screeningTool.request.ScreeningToolRequest
import it.weMake.covid19Companion.models.washHandsReminderLocations.WashHandsReminderLocation
import it.wemake.covid19Companion.domain.models.appReleases.AppReleaseDomainModel
import it.wemake.covid19Companion.domain.models.screeningTool.requests.EvidenceDomainModel
import it.wemake.covid19Companion.domain.models.screeningTool.requests.ScreeningToolRequestDomainModel
import it.wemake.covid19Companion.domain.models.washHandsReminderLocations.WashHandsReminderLocationDomainModel

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

fun WashHandsReminderLocation.toDomain(): WashHandsReminderLocationDomainModel =
    WashHandsReminderLocationDomainModel(
        id,
        name,
        address,
        lat,
        lng,
        enabled
    )

fun AppRelease.toDomain(): AppReleaseDomainModel =
    AppReleaseDomainModel(
        versionName,
        versionDetails
    )