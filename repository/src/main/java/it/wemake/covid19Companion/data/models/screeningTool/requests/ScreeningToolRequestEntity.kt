package it.wemake.covid19Companion.data.models.screeningTool.requests

data class ScreeningToolRequestEntity(
    val sex: String,
  val age: Int,
  val evidence: List<EvidenceEntity>
)