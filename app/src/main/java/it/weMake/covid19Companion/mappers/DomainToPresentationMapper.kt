package it.weMake.covid19Companion.mappers

import it.weMake.covid19Companion.models.AreaCasesData
import it.weMake.covid19Companion.models.CountryCasesData
import it.weMake.covid19Companion.models.CountryInfo
import it.weMake.covid19Companion.models.GlobalStats
import it.wemake.covid19Companion.domain.models.AreaCasesDataDomainModel
import it.wemake.covid19Companion.domain.models.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.models.CountryInfoDomainModel
import it.wemake.covid19Companion.domain.models.GlobalStatsDomainModel

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