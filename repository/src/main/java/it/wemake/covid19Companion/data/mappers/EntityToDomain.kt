package it.wemake.covid19Companion.data.mappers

import it.wemake.covid19Companion.data.models.*
import it.wemake.covid19Companion.domain.models.*



internal fun CountryCasesDataEntity.toDomain(): CountryCasesDomainModel =
    CountryCasesDomainModel(
        displayName,
        lastUpdated,
        countryInfo.toDomain(),
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        totalConfirmedDelta,
        totalDeathsDelta,
        totalRecoveredDelta,
        continent
    )

internal fun CountryEntityModel.toDomain(): CountryDomainModel =
    CountryDomainModel(
        name,
        iso2
    )

internal fun CountryInfoEntity.toDomain(): CountryInfoDomainModel =
    CountryInfoDomainModel(
        iso2,
        iso3
    )

internal fun GlobalStatsEntity.toDomain(): GlobalStatsDomainModel =
    GlobalStatsDomainModel(
        confirmed,
        recovered,
        deaths
    )

internal fun PreventionTipEntity.toDomain(): PreventionTipDomainModel =
    PreventionTipDomainModel(
        title,
        preventionTip,
        preventionTipWhy,
        iconId
    )