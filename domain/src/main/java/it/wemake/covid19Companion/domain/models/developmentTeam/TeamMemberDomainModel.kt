package it.wemake.covid19Companion.domain.models.developmentTeam

data class TeamMemberDomainModel(
    val name: String,
    val role: String,
    val about: String,
    val externalLink: String
)