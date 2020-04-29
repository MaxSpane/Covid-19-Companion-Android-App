package it.wemake.covid19Companion.remote.mappers

import it.wemake.covid19Companion.data.models.NovelCountryCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryInfoEntity
import it.wemake.covid19Companion.remote.models.casesData.CountryCasesDataRemoteModel
import it.wemake.covid19Companion.remote.models.casesData.CountryInfoRemoteModel

internal fun CountryCasesDataRemoteModel.toEntity(): NovelCountryCasesDataEntity =
    NovelCountryCasesDataEntity(
        updated,
        country,
        countryInfo.toEntity(),
        cases,
        todayCases,
        deaths,
        todayDeaths,
        recovered,
        continent
    )

internal fun CountryInfoRemoteModel.toEntity(): CountryInfoEntity =
    CountryInfoEntity(
        iso2?.let { it } ?: "",
        iso3.let { it } ?: ""
    )