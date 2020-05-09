package it.wemake.covid19Companion.remote.models.screeningTool

data class QuestionItemRemoteModel(
    val id: String,
    val name: String,
    val explanation: String?,
    val choices: List<ChoiceRemoteModel>
)