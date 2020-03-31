package it.weMake.covid19Companion.mappers

import it.weMake.covid19Companion.models.Country
import it.wemake.covid19Companion.domain.models.CountryDomainModel

fun Country.toDomain(): CountryDomainModel =
    CountryDomainModel(
        this.slug,
        this.country
    )