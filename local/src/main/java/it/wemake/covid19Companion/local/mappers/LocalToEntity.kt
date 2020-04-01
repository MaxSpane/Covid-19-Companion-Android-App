package it.wemake.covid19Companion.local.mappers

import it.wemake.covid19Companion.data.models.CasesStatsEntity
import it.wemake.covid19Companion.data.models.CountryCasesEntity
import it.wemake.covid19Companion.local.models.CasesStatsLocal
import it.wemake.covid19Companion.local.models.CountryCasesLocalModel

internal fun CountryCasesLocalModel.toEntity(): CountryCasesEntity =
    CountryCasesEntity(
        this.country,
        this.slug,
        this.newConfirmed,
        this.totalConfirmed,
        this.newDeaths,
        this.totalDeaths,
        this.newRecovered,
        this.totalRecovered
    )

internal fun CasesStatsLocal.toEntity(): CasesStatsEntity =
    CasesStatsEntity(
        this.allConfirmedCases,
        this.allRecovered,
        this.allDeaths
    )