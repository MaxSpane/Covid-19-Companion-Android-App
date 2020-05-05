package it.wemake.covid19Companion.remote.models.screeningTool.request

data class ScreeningToolRequestRemoteModel(
    val sex: String,
    val age: Int,
    val evidence: List<EvidenceRemoteModel>
)