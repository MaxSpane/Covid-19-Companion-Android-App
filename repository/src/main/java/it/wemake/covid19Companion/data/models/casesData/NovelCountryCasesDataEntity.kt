package it.wemake.covid19Companion.data.models.casesData

import it.wemake.covid19Companion.data.models.CountryInfoEntity

data class NovelCountryCasesDataEntity (
    val updated: Long,
    val country: String,
    val countryInfo: CountryInfoEntity,
    val cases: Int,
    val todayCases: Int,
    val deaths: Int,
    val todayDeaths: Int,
    val recovered: Int,
    val continent: String
)