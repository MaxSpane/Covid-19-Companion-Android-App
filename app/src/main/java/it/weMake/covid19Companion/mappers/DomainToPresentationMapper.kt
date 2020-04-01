package it.weMake.covid19Companion.mappers

import it.weMake.covid19Companion.models.CasesStats
import it.weMake.covid19Companion.models.Country
import it.weMake.covid19Companion.models.CountryCases
import it.wemake.covid19Companion.domain.models.CasesStatsDomain
import it.wemake.covid19Companion.domain.models.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.models.CountryDomainModel

fun CountryDomainModel.toPresentation(): Country =
    Country(
        this.slug,
        this.country
    )

fun CountryCasesDomainModel.toPresentation(): CountryCases =
    CountryCases(
        this.country,
        this.slug,
        this.newConfirmed,
        this.totalConfirmed,
        this.newDeaths,
        this.totalDeaths,
        this.newRecovered,
        this.totalRecovered
    )

fun CasesStatsDomain.toPresentation(): CasesStats =
    CasesStats(
        this.allConfirmedCases,
        this.allRecovered,
        this.allDeaths
    )