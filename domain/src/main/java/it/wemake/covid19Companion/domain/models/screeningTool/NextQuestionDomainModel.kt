package it.wemake.covid19Companion.domain.models.screeningTool

data class NextQuestionDomainModel(
    val shouldStop: Boolean,
    val question: QuestionDomainModel?
)