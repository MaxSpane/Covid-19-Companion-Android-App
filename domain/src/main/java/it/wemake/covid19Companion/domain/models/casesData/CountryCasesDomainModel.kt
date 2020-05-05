package it.wemake.covid19Companion.domain.models.casesData

import it.wemake.covid19Companion.domain.models.CountryInfoDomainModel

data class CountryCasesDomainModel(
    val displayName: String,
    val lastUpdated: Long,
    val countryInfo: CountryInfoDomainModel,
    val totalConfirmed: Int?,
    val totalDeaths: Int?,
    val totalRecovered: Int?,
    val totalConfirmedDelta: Int?,
    val totalDeathsDelta: Int?,
    val totalRecoveredDelta: Int?,
    val continent: String
)