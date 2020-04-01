package it.wemake.covid19Companion.local.models

data class CasesStatsLocal(
    val allConfirmedCases: Int,
    val allRecovered: Int,
    val allDeaths: Int
)