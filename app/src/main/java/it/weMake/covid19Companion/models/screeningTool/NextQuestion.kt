package it.weMake.covid19Companion.models.screeningTool

data class NextQuestion(
    val shouldStop: Boolean,
    val question: Question?
)