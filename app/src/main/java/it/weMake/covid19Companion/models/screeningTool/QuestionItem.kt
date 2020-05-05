package it.weMake.covid19Companion.models.screeningTool

data class QuestionItem(
    val id: String,
    val name: String,
    val explanation: String?,
    val choices: List<Choice>
)