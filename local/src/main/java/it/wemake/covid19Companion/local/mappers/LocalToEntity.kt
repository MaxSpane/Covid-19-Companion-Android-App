package it.wemake.covid19Companion.local.mappers

import it.wemake.covid19Companion.data.models.*
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
        continent
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