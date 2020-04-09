package it.wemake.covid19Companion.local.mappers

import it.wemake.covid19Companion.data.models.AreaCasesDataEntity
import it.wemake.covid19Companion.data.models.CasesStatsEntity
import it.wemake.covid19Companion.data.models.CountryCasesEntity
import it.wemake.covid19Companion.local.models.CasesStatsLocal
import it.wemake.covid19Companion.local.models.AreaCasesDataLocalModel

internal fun AreaCasesDataLocalModel.toEntity(): AreaCasesDataEntity =
    AreaCasesDataEntity(
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

internal fun CasesStatsLocal.toEntity(): CasesStatsEntity =
    CasesStatsEntity(
        this.allConfirmedCases,
        this.allRecovered,
        this.allDeaths
    )