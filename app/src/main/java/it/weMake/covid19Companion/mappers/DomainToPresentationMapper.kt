package it.weMake.covid19Companion.mappers

import it.weMake.covid19Companion.models.CasesStats
import it.weMake.covid19Companion.models.Country
import it.weMake.covid19Companion.models.AreaCasesData
import it.wemake.covid19Companion.domain.models.CasesStatsDomain
import it.wemake.covid19Companion.domain.models.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.models.AreaCasesDataDomainModel

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
