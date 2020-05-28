package it.wemake.covid19Companion.local.mappers

import it.wemake.covid19Companion.data.models.casesData.NovelCountryCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryInfoEntity
import it.wemake.covid19Companion.local.models.CountryCasesDataLocalModel
import it.wemake.covid19Companion.local.models.CountryInfoLocalModel

internal fun NovelCountryCasesDataEntity.toLocal(): CountryCasesDataLocalModel =
    CountryCasesDataLocalModel(
        country,
        cases,
        deaths,
        recovered,
        todayCases,
        todayDeaths,
        0,
        updated,
        continent,
        countryInfo.toLocal(),
        casesPerOneMillion,
        deathsPerOneMillion,
        recoveredPerOneMillion
    )

internal fun CountryInfoEntity.toLocal(): CountryInfoLocalModel =
    CountryInfoLocalModel(
        iso2,
        iso3
    )