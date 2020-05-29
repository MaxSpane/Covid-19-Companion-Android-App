package it.wemake.covid19Companion.local.mappers

import it.wemake.covid19Companion.data.models.casesData.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryInfoEntity
import it.wemake.covid19Companion.data.models.casesData.RegionCasesDataEntity
import it.wemake.covid19Companion.local.models.CountryCasesDataLocalModel
import it.wemake.covid19Companion.local.models.CountryInfoLocalModel
import it.wemake.covid19Companion.local.models.RegionCasesDataLocalModel

internal fun CountryCasesDataEntity.toLocal(): CountryCasesDataLocalModel =
    CountryCasesDataLocalModel(
        displayName,
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        totalConfirmedDelta,
        totalDeathsDelta,
        totalRecoveredDelta,
        lastUpdated,
        continent,
        countryInfo.toLocal(),
        casesPerOneMillion,
        deathsPerOneMillion,
        recoveredPerOneMillion,
        hasRegionalCasesData
    )

internal fun CountryInfoEntity.toLocal(): CountryInfoLocalModel =
    CountryInfoLocalModel(
        iso2,
        iso3
    )

internal fun RegionCasesDataEntity.toLocal(): RegionCasesDataLocalModel =
    RegionCasesDataLocalModel(
        displayName,
        updated,
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        parentCountryName
    )