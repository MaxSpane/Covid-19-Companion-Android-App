package it.wemake.covid19Companion.remote.mappers

import it.wemake.covid19Companion.data.models.CasesSummaryEntity
import it.wemake.covid19Companion.data.models.CountryCasesEntity
import it.wemake.covid19Companion.remote.models.casesSummary.CasesSummaryRemoteModel
import it.wemake.covid19Companion.remote.models.casesSummary.CountryCasesRemoteModel

internal fun CasesSummaryRemoteModel.toEntity(): CasesSummaryEntity =
    CasesSummaryEntity(
        this.Countries.map {
            it.toEntity()
        },
        this.Date
    )

internal fun CountryCasesRemoteModel.toEntity(): CountryCasesEntity =
    CountryCasesEntity(
        this.Country,
        this.Slug,
        this.NewConfirmed,
        this.TotalConfirmed,
        this.NewDeaths,
        this.TotalDeaths,
        this.NewRecovered,
        this.TotalRecovered
    )
