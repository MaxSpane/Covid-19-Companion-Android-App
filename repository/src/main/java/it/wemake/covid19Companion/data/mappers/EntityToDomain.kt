package it.wemake.covid19Companion.data.mappers

import it.wemake.covid19Companion.data.models.AreaCasesDataEntity
import it.wemake.covid19Companion.data.models.CasesStatsEntity
import it.wemake.covid19Companion.data.models.CountryCasesEntity
import it.wemake.covid19Companion.domain.models.*


internal fun AreaCasesDataEntity.toDomain(): AreaCasesDataDomainModel =
    AreaCasesDataDomainModel(
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

internal fun CasesStatsEntity.toDomain(): CasesStatsDomain =
    CasesStatsDomain(
        this.allConfirmedCases,
        this.allRecovered,
        this.allDeaths
    )
