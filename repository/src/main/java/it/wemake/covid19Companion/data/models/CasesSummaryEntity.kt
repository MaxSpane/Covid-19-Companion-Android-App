package it.wemake.covid19Companion.data.models

import it.wemake.covid19Companion.data.models.CountryCasesEntity

data class CasesSummaryEntity(
    val countries: List<CountryCasesEntity>,
    val date: String
)