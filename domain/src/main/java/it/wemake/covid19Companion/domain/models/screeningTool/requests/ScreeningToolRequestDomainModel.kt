package it.wemake.covid19Companion.domain.models.screeningTool.requests

data class ScreeningToolRequestDomainModel(
    val sex: String,
    val age: Int,
    val evidence: List<EvidenceDomainModel>
)