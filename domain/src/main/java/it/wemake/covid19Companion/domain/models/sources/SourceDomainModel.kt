package it.wemake.covid19Companion.domain.models.sources

data class SourceDomainModel(
    val sourceName: String,
    val sourceDescription: String,
    val resources: String,
    val sourceExternalLink: String
)