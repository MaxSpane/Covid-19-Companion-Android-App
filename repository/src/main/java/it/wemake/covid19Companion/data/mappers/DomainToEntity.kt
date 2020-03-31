package it.wemake.covid19Companion.data.mappers

import it.wemake.covid19Companion.data.models.CountryEntityModel
import it.wemake.covid19Companion.domain.models.CountryDomainModel

internal fun CountryDomainModel.toEntity(): CountryEntityModel =
    CountryEntityModel(
        this.slug,
        this.country
    )