package it.wemake.covid19Companion.data.models.screeningTool

data class NextQuestionEntity(
    val shouldStop: Boolean,
    val question: QuestionEntity?
)