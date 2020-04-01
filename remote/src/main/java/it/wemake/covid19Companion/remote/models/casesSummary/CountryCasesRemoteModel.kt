package it.wemake.covid19Companion.remote.models.casesSummary

data class CountryCasesRemoteModel(
    val Country: String,
    val Slug: String,
    val NewConfirmed: Int,
    val TotalConfirmed: Int,
    val NewDeaths: Int,
    val TotalDeaths: Int,
    val NewRecovered: Int,
    val TotalRecovered: Int
)