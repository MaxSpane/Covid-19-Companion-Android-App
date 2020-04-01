package it.weMake.covid19Companion.models

data class CasesStats(
    val allConfirmedCases: Int,
    val allRecovered: Int,
    val allDeaths: Int
)