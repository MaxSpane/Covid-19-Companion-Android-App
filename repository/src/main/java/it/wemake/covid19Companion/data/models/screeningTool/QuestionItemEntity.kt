package it.wemake.covid19Companion.data.models.screeningTool

data class QuestionItemEntity(
    val id: String,
    val name: String,
    val explanation: String?,
    val choices: List<ChoiceEntity>
)