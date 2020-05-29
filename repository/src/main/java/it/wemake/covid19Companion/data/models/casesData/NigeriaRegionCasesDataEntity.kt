package it.wemake.covid19Companion.data.models.casesData

data class NigeriaRegionCasesDataEntity(
    val updated: Long,
    val state: String,
    val cases: Int,
    val recovered: Int,
    val deaths: Int
)