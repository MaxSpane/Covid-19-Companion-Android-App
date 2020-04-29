package it.wemake.covid19Companion.local.models

data class GlobalStatsLocalModel(
    val confirmed: Int,
    val recovered: Int,
    val deaths: Int
)