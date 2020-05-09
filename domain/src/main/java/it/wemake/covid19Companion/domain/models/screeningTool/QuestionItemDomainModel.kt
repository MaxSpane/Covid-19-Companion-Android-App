package it.wemake.covid19Companion.domain.models.screeningTool

data class QuestionItemDomainModel(
    val id: String,
    val name: String,
    val explanation: String?,
    val choices: List<ChoiceDomainModel>
)