package it.wemake.covid19Companion.remote.models.casesData

data class NigeriaRegionCasesDataRemoteModel(
    val updated: Long,
    val state: String,
    val cases: Int,
    val recovered: Int,
    val deaths: Int
)