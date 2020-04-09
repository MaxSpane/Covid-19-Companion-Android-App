package it.wemake.covid19Companion.local.mappers

import it.wemake.covid19Companion.data.models.AreaCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryCasesEntity
import it.wemake.covid19Companion.local.models.AreaCasesDataLocalModel

internal fun AreaCasesDataEntity.toLocal(): AreaCasesDataLocalModel =
    AreaCasesDataLocalModel(
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