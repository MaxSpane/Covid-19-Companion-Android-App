package it.wemake.covid19Companion.remote.models.casesSummary

data class CasesSummaryRemoteModel(
    val Countries: List<CountryCasesRemoteModel>,
    val Date: String
)