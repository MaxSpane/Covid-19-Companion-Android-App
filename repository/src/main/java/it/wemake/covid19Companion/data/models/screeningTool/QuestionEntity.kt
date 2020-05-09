package it.wemake.covid19Companion.data.models.screeningTool

data class QuestionEntity(
    val explanation: String?,
    val text: String,
    val type: String,
    val items: List<QuestionItemEntity>
)