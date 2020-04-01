package it.wemake.covid19Companion.data.models

data class CasesStatsEntity(
    val allConfirmedCases: Int,
    val allRecovered: Int,
    val allDeaths: Int
)