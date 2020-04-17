package it.wemake.covid19Companion.local.mappers

import it.wemake.covid19Companion.data.models.AreaCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryEntityModel
import it.wemake.covid19Companion.local.models.AreaCasesDataLocalModel
import it.wemake.covid19Companion.local.models.CountriesCasesDataLocalModel
import it.wemake.covid19Companion.local.models.CountryLocalModel

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

internal fun CountriesCasesDataLocalModel.toEntity(): CountryCasesDataEntity =
    CountryCasesDataEntity(
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

internal fun CountryLocalModel.toEntity(): CountryEntityModel =
    CountryEntityModel(
        name,
        iso2
    )