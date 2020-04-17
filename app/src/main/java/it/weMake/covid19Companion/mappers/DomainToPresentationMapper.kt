package it.weMake.covid19Companion.mappers

import it.weMake.covid19Companion.models.AreaCasesData
import it.weMake.covid19Companion.models.CountryCasesData
import it.wemake.covid19Companion.domain.models.AreaCasesDataDomainModel
import it.wemake.covid19Companion.domain.models.CountryCasesDomainModel

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
        id,
        displayName,
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        totalConfirmedDelta,
        totalDeathsDelta,
        totalRecoveredDelta,
        hasAreasData,
        iso2
    )
