package it.wemake.covid19Companion.domain.models.screeningTool

data class QuestionDomainModel(
    val explanation: String?,
    val text: String,
    val type: String,
    val items: List<QuestionItemDomainModel>
)