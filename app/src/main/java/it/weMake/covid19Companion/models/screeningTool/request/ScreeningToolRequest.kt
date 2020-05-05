package it.weMake.covid19Companion.models.screeningTool.request

data class ScreeningToolRequest(
    val sex: String,
    val age: Int,
    val evidence: List<Evidence>
)