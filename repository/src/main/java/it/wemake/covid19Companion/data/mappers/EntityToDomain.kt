package it.wemake.covid19Companion.data.mappers

import it.wemake.covid19Companion.data.models.AreaCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryEntityModel
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

internal fun CountryCasesDataEntity.toDomain(): CountryCasesDomainModel =
    CountryCasesDomainModel(
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

internal fun CountryEntityModel.toDomain(): CountryDomainModel =
    CountryDomainModel(
        name,
        iso2
    )