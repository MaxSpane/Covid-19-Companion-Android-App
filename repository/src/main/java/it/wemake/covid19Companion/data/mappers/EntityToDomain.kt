package it.wemake.covid19Companion.data.mappers

import it.wemake.covid19Companion.data.models.CasesStatsEntity
import it.wemake.covid19Companion.data.models.CountryCasesEntity
import it.wemake.covid19Companion.domain.models.*


internal fun CountryCasesEntity.toDomain(): CountryCasesDomainModel =
    CountryCasesDomainModel(
        this.country,
        this.slug,
        this.newConfirmed,
        this.totalConfirmed,
        this.newDeaths,
        this.totalDeaths,
        this.newRecovered,
        this.totalRecovered
    )

internal fun CasesStatsEntity.toDomain(): CasesStatsDomain =
    CasesStatsDomain(
        this.allConfirmedCases,
        this.allRecovered,
        this.allDeaths
    )
