package it.wemake.covid19Companion.domain.models

data class CasesStatsDomain(
    val allConfirmedCases: Int,
    val allRecovered: Int,
    val allDeaths: Int
)