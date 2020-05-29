package it.wemake.covid19Companion.local.mappers

import it.wemake.covid19Companion.data.models.*
import it.wemake.covid19Companion.data.models.casesData.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.casesData.GlobalStatsEntity
import it.wemake.covid19Companion.data.models.casesData.RegionCasesDataEntity
import it.wemake.covid19Companion.data.models.preventionTips.PreventionTipEntity
import it.wemake.covid19Companion.data.models.washHandsReminderLocations.WashHandsReminderLocationEntity
import it.wemake.covid19Companion.local.models.*

internal fun CountryCasesDataLocalModel.toEntity(): CountryCasesDataEntity =
    CountryCasesDataEntity(
        displayName,
        updated,
        countryInfo.toEntity(),
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        totalConfirmedDelta,
        totalDeathsDelta,
        totalRecoveredDelta,
        continent,
        casesPerOneMillion,
        deathsPerOneMillion,
        recoveredPerOneMillion,
        hasRegionalCasesData
    )

internal fun CountryLocalModel.toEntity(): CountryEntityModel =
    CountryEntityModel(
        name,
        iso2
    )

internal fun CountryInfoLocalModel.toEntity(): CountryInfoEntity =
    CountryInfoEntity(
        iso2,
        iso3
    )

internal fun GlobalStatsLocalModel.toEntity(): GlobalStatsEntity =
    GlobalStatsEntity(
        confirmed,
        recovered,
        deaths
    )

internal fun PreventionTipLocalModel.toEntity(): PreventionTipEntity =
    PreventionTipEntity(
        title,
        preventionTip,
        preventionTipWhy,
        iconId
    )

internal fun WashHandsReminderLocationLocalModel.toEntity(): WashHandsReminderLocationEntity =
    WashHandsReminderLocationEntity(
        id,
        name,
        address,
        lat,
        lng,
        enabled
    )

internal fun RegionCasesDataLocalModel.toEntity(): RegionCasesDataEntity =
    RegionCasesDataEntity(
        displayName,
        updated,
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        parentCountryName
    )