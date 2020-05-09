package it.wemake.covid19Companion.domain.models.screeningTool

data class DiagnosisDomainModel(
    val diagnosisLevel: String,
    val label: String,
    val description: String,
    val observations: List<ObservationDomainModel>
)