package it.wemake.covid19Companion.data.models.screeningTool

data class DiagnosisEntity(
    val diagnosisLevel: String,
    val label: String,
    val description: String,
    val observations: List<ObservationEntity>
)