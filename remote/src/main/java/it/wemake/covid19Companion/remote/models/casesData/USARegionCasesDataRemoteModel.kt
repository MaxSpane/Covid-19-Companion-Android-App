package it.wemake.covid19Companion.remote.models.casesData

data class USARegionCasesDataRemoteModel(
    val updated: Long,
    val state: String,
    val cases: Int?,
    val todayCases: Int?,
    val deaths: Int?,
    val todayDeaths: Int?,
    val recovered: Int?,
    val todayRecovered: Int?,
    val casesPerOneMillion: Double?,
    val deathsPerOneMillion: Double?,
    val recoveredPerOneMillion: Double?
)