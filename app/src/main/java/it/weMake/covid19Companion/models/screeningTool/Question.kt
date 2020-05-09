package it.weMake.covid19Companion.models.screeningTool

data class Question(
    val explanation: String?,
    val text: String,
    val type: String,
    val items: List<QuestionItem>
)