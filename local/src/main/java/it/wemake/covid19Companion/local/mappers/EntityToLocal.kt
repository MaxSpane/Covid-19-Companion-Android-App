package it.wemake.covid19Companion.local.mappers

import it.wemake.covid19Companion.data.models.CountryCasesEntity
import it.wemake.covid19Companion.local.models.CountryCasesLocalModel

internal fun CountryCasesEntity.toLocal(): CountryCasesLocalModel =
    CountryCasesLocalModel(
        this.slug,
        this.country,
        this.newConfirmed,
        this.totalConfirmed,
        this.newDeaths,
        this.totalDeaths,
        this.newRecovered,
        this.totalRecovered
    )