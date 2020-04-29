package it.wemake.covid19Companion.data.models

data class GlobalStatsEntity(
    val confirmed: Int,
    val recovered: Int,
    val deaths: Int
)