package it.wemake.covid19Companion.data.models.sources

data class SourceEntity(
    val sourceName: String,
    val sourceDescription: String,
    val resources: String,
    val sourceExternalLink: String
)