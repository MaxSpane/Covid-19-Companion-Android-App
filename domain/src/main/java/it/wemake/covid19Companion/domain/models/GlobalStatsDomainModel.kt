package it.wemake.covid19Companion.domain.models

data class GlobalStatsDomainModel(
    val confirmed: Int,
    val recovered: Int,
    val deaths: Int
)