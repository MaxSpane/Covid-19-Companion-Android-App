package it.wemake.covid19Companion.local.mappers

import it.wemake.covid19Companion.data.models.CountryEntityModel
import it.wemake.covid19Companion.local.models.CountryLocalModel

internal fun CountryLocalModel.toEntity(): CountryEntityModel =
    CountryEntityModel(
        this.slug,
        this.country
    )