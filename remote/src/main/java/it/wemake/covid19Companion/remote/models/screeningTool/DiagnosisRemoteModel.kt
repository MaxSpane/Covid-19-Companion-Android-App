package it.wemake.covid19Companion.remote.models.screeningTool

data class DiagnosisRemoteModel(
    val triage_level: String,
    val label: String,
    val description: String,
    val serious: List<ObservationRemoteModel>
)