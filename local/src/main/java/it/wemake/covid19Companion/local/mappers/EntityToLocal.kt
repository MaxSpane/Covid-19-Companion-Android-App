package it.wemake.covid19Companion.local.mappers

import it.wemake.covid19Companion.data.models.CountryEntityModel
import it.wemake.covid19Companion.local.models.CountryLocalModel

internal fun CountryEntityModel.toLocal(): CountryLocalModel =
    CountryLocalModel(
        this.slug,
        this.country
    )