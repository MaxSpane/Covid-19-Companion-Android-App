package it.wemake.covid19Companion.domain.models.preventionTips

data class PreventionTipDomainModel(
    val title: String,
    val preventionTip: String,
    val preventionTipWhy: String,
    val iconId: String
)