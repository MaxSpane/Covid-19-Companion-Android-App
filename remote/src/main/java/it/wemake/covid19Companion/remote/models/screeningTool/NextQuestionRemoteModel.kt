package it.wemake.covid19Companion.remote.models.screeningTool

data class NextQuestionRemoteModel(
    val should_stop: Boolean,
    val question: QuestionRemoteModel?
)