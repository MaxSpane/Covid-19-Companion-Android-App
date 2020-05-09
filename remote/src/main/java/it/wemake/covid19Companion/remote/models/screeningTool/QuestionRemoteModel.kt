package it.wemake.covid19Companion.remote.models.screeningTool

data class QuestionRemoteModel(
    val explanation: String?,
    val text: String,
    val type: String,
    val items: List<QuestionItemRemoteModel>
)