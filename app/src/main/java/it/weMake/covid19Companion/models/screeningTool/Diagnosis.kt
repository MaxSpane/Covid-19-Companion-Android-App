package it.weMake.covid19Companion.models.screeningTool

data class Diagnosis(
    val diagnosisLevel: String,
    val label: String,
    val description: String,
    val observations: List<Observation>
)