package it.wemake.covid19Companion.domain.models

data class PreventionTipDomainModel(
    val title: String,
    val preventionTip: String,
    val preventionTipWhy: String,
    val iconId: String
)