package it.weMake.covid19Companion.mappers

import it.weMake.covid19Companion.models.*
import it.wemake.covid19Companion.domain.models.*

fun AreaCasesDataDomainModel.toPresentation(): AreaCasesData =
    AreaCasesData(
        id,
        displayName,
        lastUpdated,
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        totalConfirmedDelta,
        totalDeathsDelta,
        totalRecoveredDelta,
        parentId,
        hasAreasData
    )

fun CountryCasesDomainModel.toPresentation(): CountryCasesData =
    CountryCasesData(
        displayName,
        lastUpdated,
        countryInfo.toPresentation(),
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        totalConfirmedDelta,
        totalDeathsDelta,
        totalRecoveredDelta,
        continent
    )

fun CountryInfoDomainModel.toPresentation(): CountryInfo =
    CountryInfo(
        iso2,
        iso3
    )

fun GlobalStatsDomainModel.toPresentation(): GlobalStats =
    GlobalStats(
        confirmed,
        recovered,
        deaths
    )

fun PreventionTipDomainModel.toPresentation(): PreventionTip =
    PreventionTip(
        title,
        preventionTip,
        preventionTipWhy,
        iconId
    )